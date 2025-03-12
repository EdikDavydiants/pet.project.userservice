package pet.project.userservice.service;

import org.springframework.stereotype.Service;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.request.AuthenticationDtoRequest;
import pet.project.userservice.model.dto.response.AuthenticationDtoResponse;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.UserRepository;

import static pet.project.userservice.constant.ErrorMessagesUtil.USER_NOT_FOUND;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationDtoResponse getPasswordHash(AuthenticationDtoRequest request) {
        User user = userRepository.findUserByUsername(request.username())
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return AuthenticationDtoResponse.builder()
                .id(user.getId())
                .passwordHash(user.getPasswordHash())
                .build();
    }
}
