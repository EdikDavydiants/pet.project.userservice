package pet.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.project.userservice.model.entity.Friendship;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {


}
