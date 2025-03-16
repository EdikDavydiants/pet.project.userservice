package pet.project.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
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
}
