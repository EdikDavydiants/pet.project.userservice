package pet.project.userservice.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegistrationDtoRequest(

        @NotNull
        String username,

        @NotNull
        String email,

        @NotNull
        String passwordHash,

        String name,

        String bio
) {
}