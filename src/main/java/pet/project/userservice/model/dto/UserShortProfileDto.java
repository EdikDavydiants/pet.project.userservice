package pet.project.userservice.model.dto;

import lombok.Builder;

@Builder
public record UserShortProfileDto(

        long id,

        String name,

        String avatar,

        String bio
) {
}
