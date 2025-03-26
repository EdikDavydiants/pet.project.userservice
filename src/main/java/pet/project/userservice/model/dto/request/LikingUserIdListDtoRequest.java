package pet.project.userservice.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record LikingUserIdListDtoRequest(

        @NotNull
        List<Long> userIds
) {
}
