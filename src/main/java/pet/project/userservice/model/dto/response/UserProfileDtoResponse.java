package pet.project.userservice.model.dto.response;

import lombok.Builder;

@Builder
public record UserProfileDtoResponse(

        long id,

        String username,

        String email,

        String name,

        String avatar,

        String bio
) {
}
