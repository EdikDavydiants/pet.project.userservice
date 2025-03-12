package pet.project.userservice.model.dto.request;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDtoRequest (

        @NotNull
        String username
) {
}
