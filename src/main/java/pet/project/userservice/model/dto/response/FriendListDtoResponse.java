package pet.project.userservice.model.dto.response;

import lombok.Builder;
import pet.project.userservice.model.dto.UserShortProfileDto;

import java.util.List;

@Builder
public record FriendListDtoResponse(

        List<UserShortProfileDto> friends,

        int count
) {
}
