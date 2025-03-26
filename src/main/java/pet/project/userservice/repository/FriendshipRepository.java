package pet.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.project.userservice.model.entity.Friendship;
import pet.project.userservice.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findFriendshipByUserAndFriend(User user, User friend);

    @Query("""
        SELECT f.friend.id FROM Friendship f
        WHERE f.user.id = ?1
        AND f.friendshipStatus = 'ACCEPTED'
        
        UNION
        
        SELECT f.user.id FROM Friendship f
        WHERE f.friend.id = ?1
        AND f.friendshipStatus = 'ACCEPTED'
        """)
    List<Long> findFriendIds(long userId);
}
