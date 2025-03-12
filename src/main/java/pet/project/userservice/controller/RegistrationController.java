package pet.project.userservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.project.userservice.model.dto.request.RegistrationDtoRequest;
import pet.project.userservice.model.dto.response.RegistrationDtoResponse;
import pet.project.userservice.service.RegistrationService;

@RestController
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public RegistrationDtoResponse saveNewUser(@Valid @RequestBody RegistrationDtoRequest request) {

        return registrationService.saveNewUser(request);
    }
}
