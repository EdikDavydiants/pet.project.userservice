package pet.project.userservice.model.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FriendListDtoResponse(

        List<UserShortProfileDtoResponse> friends,

        int count
) {
}
