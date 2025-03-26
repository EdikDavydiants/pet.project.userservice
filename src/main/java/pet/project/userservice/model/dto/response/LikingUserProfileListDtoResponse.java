package pet.project.userservice.model.dto.response;

import pet.project.userservice.model.dto.UserShortProfileDto;

import java.util.List;

public record LikingUserProfileListDtoResponse(

        List<UserShortProfileDto> users
) {
}
