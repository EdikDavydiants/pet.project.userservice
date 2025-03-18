package pet.project.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.userservice.exception.BadRequestException;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.response.FriendshipRequestDtoResponse;
import pet.project.userservice.model.entity.Friendship;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.FriendshipRepository;
import pet.project.userservice.repository.UserRepository;

import java.time.Instant;

import static pet.project.userservice.constant.ErrorMessagesUtil.FRIEND_ID_MATCH_USER_ID;
import static pet.project.userservice.constant.ErrorMessagesUtil.USER_NOT_FOUND;
import static pet.project.userservice.enums.FriendshipStatus.PENDING;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;

    public FriendshipRequestDtoResponse sendFriendshipRequest(long id, long friendId) {

        if (id == friendId) {
            throw new BadRequestException(FRIEND_ID_MATCH_USER_ID);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        var friendship = Friendship.builder()
                .user(user)
                .friend(friend)
                .friendshipStatus(PENDING)
                .createdAt(Instant.now())
                .build();

        friendshipRepository.save(friendship);

        return new FriendshipRequestDtoResponse(PENDING.toString());
    }
}
