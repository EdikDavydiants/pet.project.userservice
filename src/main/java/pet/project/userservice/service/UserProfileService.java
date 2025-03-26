package pet.project.userservice.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.userservice.exception.AllParamsAreNullException;
import pet.project.userservice.exception.ForbiddenAccessException;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.request.UserProfileUpdateDtoRequest;
import pet.project.userservice.model.dto.response.LikingUserProfileListDtoResponse;
import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.model.dto.UserShortProfileDto;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.UserRepository;
import pet.project.userservice.utils.JwtUtils;

import java.util.List;

import static pet.project.userservice.constant.ErrorMessagesUtil.*;
import static pet.project.userservice.mapper.UserMappers.*;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileDtoResponse getUserProfile(String jwt, long id) {

        long idFromHeader = JwtUtils.extractIdFromJwt(jwt);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        if (idFromHeader == id) {
            return mapUserToFullUserProfileDto(user);
        } else {
            return mapUserToUserProfileDto(user);
        }
    }

    public void updateUserProfile(String jwt, long id, UserProfileUpdateDtoRequest request) {

        long idFromHeader = JwtUtils.extractIdFromJwt(jwt);

        if (idFromHeader != id) {
            throw new ForbiddenAccessException(FORBIDDEN_TO_UPDATE);
        }

        if (request.name() == null && request.bio() == null && request.avatar() == null) {
            throw new AllParamsAreNullException(ALL_PARAMS_ARE_NULL);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.bio() != null) {
            user.setBio(request.bio());
        }
        if (request.avatar() != null) {
            user.setAvatar(request.avatar());
        }

        userRepository.save(user);
    }

    public List<UserShortProfileDto> searchUserProfiles(String query) {

        List<User> users = userRepository.findTop15ByNameContainingIgnoreCase(query);
        return mapUsersToShortProfiles(users);
    }

    public LikingUserProfileListDtoResponse getUserProfiles(@NotNull List<Long> usersIds) {

        var userList = userRepository.findAllById(usersIds);
        var userDtoList = mapUsersToShortProfiles(userList);
        return new LikingUserProfileListDtoResponse(userDtoList);
    }
}
