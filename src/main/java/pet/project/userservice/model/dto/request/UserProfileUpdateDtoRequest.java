package pet.project.userservice.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserProfileUpdateDtoRequest(

        @NotBlank
        String name,

        String bio,

        String avatar
) {
}
