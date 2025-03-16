package pet.project.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.userservice.exception.AllParamsAreNullException;
import pet.project.userservice.exception.ForbiddenAccessException;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.request.UserProfileUpdateDtoRequest;
import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.UserRepository;
import pet.project.userservice.utils.JwtUtils;

import static pet.project.userservice.constant.ErrorMessagesUtil.*;
import static pet.project.userservice.mapper.UserMappers.mapUserToFullUserProfileDto;
import static pet.project.userservice.mapper.UserMappers.mapUserToUserProfileDto;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public UserProfileDtoResponse getUserProfile(String jwt, long id) {

        long idFromHeader = JwtUtils.extractIdFromJwt(jwt);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

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
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

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
}
