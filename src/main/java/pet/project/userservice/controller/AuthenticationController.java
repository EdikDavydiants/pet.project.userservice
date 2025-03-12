package pet.project.userservice.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pet.project.userservice.model.dto.request.AuthenticationDtoRequest;
import pet.project.userservice.model.dto.response.AuthenticationDtoResponse;
import pet.project.userservice.service.AuthenticationService;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthenticationDtoResponse getUserPasswordHash(@Valid @RequestBody AuthenticationDtoRequest request) {

        return authenticationService.getPasswordHash(request);
    }
}
