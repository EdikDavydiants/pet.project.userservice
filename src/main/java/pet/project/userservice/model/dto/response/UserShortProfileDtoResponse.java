package pet.project.userservice.model.dto.response;

import lombok.Builder;

@Builder
public record UserShortProfileDtoResponse(

        long id,

        String name,

        String avatar,

        String bio
) {
}
