package pet.project.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.userservice.exception.BadRequestException;
import pet.project.userservice.exception.FriendshipNotFoundException;
import pet.project.userservice.exception.UserNotFoundException;
import pet.project.userservice.model.dto.SimpleDtoResponse;
import pet.project.userservice.model.dto.response.FriendListDtoResponse;
import pet.project.userservice.model.entity.Friendship;
import pet.project.userservice.model.entity.User;
import pet.project.userservice.repository.FriendshipRepository;
import pet.project.userservice.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static pet.project.userservice.constant.ErrorMessagesUtil.FRIENDSHIP_NOT_FOUND;
import static pet.project.userservice.constant.ErrorMessagesUtil.FRIEND_ID_MATCH_USER_ID;
import static pet.project.userservice.constant.ErrorMessagesUtil.USER_NOT_FOUND_BY_ID;
import static pet.project.userservice.constant.SimpleMessagesUtil.FRIENDSHIP_DELETED;
import static pet.project.userservice.enums.FriendshipStatus.ACCEPTED;
import static pet.project.userservice.enums.FriendshipStatus.PENDING;
import static pet.project.userservice.mapper.UserMappers.mapUsersToShortProfiles;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;

    private final UserRepository userRepository;

    public SimpleDtoResponse sendFriendshipRequest(long id, long friendId) {

        if (id == friendId) {
            throw new BadRequestException(FRIEND_ID_MATCH_USER_ID);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        var friendship = Friendship.builder()
                .user(user)
                .friend(friend)
                .friendshipStatus(PENDING)
                .createdAt(Instant.now())
                .build();

        friendshipRepository.save(friendship);
        return new SimpleDtoResponse(PENDING.toString());
    }

    public SimpleDtoResponse acceptFriendshipRequest(long id, long friendId) {

        Friendship friendship = findFriendship(id, friendId);
        friendship.setFriendshipStatus(ACCEPTED);
        friendshipRepository.save(friendship);
        return new SimpleDtoResponse(ACCEPTED.toString());
    }

    public SimpleDtoResponse deleteFriendship(long id, long friendId) {

        Friendship friendship = findFriendship(id, friendId);
        friendshipRepository.delete(friendship);
        return new SimpleDtoResponse(FRIENDSHIP_DELETED);
    }

    private Friendship findFriendship(long id, long friendId) {

        if (id == friendId) {
            throw new BadRequestException(FRIEND_ID_MATCH_USER_ID);
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        return friendshipRepository.findFriendshipByUserAndFriend(friend, user)
                .orElseThrow(() -> new FriendshipNotFoundException(FRIENDSHIP_NOT_FOUND));
    }

    public FriendListDtoResponse getFriends(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_BY_ID));

        List<User> friends = Stream.of(user.getInvitationsToUser(), user.getInvitedByUser())
                .flatMap(friendships -> friendships.stream()
                        .filter((friendship) -> friendship.getFriendshipStatus() == ACCEPTED)
                        .map(friendship -> {
                            if (friendship.getUser().getId() == id) {
                                return friendship.getFriend();
                            } else {
                                return friendship.getUser();
                            }
                        }))
                .toList();

        return FriendListDtoResponse.builder()
                .friends(mapUsersToShortProfiles(friends))
                .count(friends.size())
                .build();
    }
}
