package pet.project.userservice.service;

import org.springframework.stereotype.Service;
import pet.project.userservice.exception.UserAlreadyExistsException;
import pet.project.userservice.model.dto.request.RegistrationDtoRequest;
import pet.project.userservice.model.dto.response.RegistrationDtoResponse;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.UserRepository;

import java.time.Instant;

import static pet.project.userservice.constant.ErrorMessagesUtil.USER_ALREADY_EXIST;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RegistrationDtoResponse saveNewUser(RegistrationDtoRequest request) {

        var newUser = User.builder()
                .username(request.username())
                .email(request.email())
                .passwordHash(request.passwordHash())
                .name(request.name())
                .bio(request.bio())
                .createdAt(Instant.now())
                .build();

        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException(USER_ALREADY_EXIST);
        }

        var savedUser = userRepository.save(newUser);

        return new RegistrationDtoResponse(savedUser.getId());
    }
}
