package pet.project.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.UserRepository;
import pet.project.userservice.utils.JwtUtils;

import static pet.project.userservice.constant.ErrorMessagesUtil.USER_NOT_FOUND;
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
}
