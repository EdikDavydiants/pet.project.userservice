package pet.project.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.userservice.model.dto.request.UserProfileUpdateDtoRequest;
import pet.project.userservice.model.dto.response.UserProfileDtoResponse;
import pet.project.userservice.service.UserProfileService;
import pet.project.userservice.utils.JwtUtils;

@RestController
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping("/{id}")
    public UserProfileDtoResponse getUserProfile(
            @RequestHeader(value = "Authorization") String authHeader, @PathVariable long id) {

        String jwt = JwtUtils.extractTokenFromHeader(authHeader);
        return userProfileService.getUserProfile(jwt, id);
    }

    @PutMapping("/{id}")
    public void updateUserProfile(
            @RequestHeader(value = "Authorization") String authHeader, @PathVariable long id,
            @Valid @RequestBody UserProfileUpdateDtoRequest request) {

        String jwt = JwtUtils.extractTokenFromHeader(authHeader);
        userProfileService.updateUserProfile(jwt, id, request);
    }
}
