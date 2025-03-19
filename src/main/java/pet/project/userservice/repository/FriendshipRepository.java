package pet.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.project.userservice.model.entity.Friendship;
import pet.project.userservice.model.entity.User;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    Optional<Friendship> findFriendshipByUserAndFriend(User user, User friend);
}
