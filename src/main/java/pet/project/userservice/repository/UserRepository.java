package pet.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.project.userservice.model.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(long id);

    boolean existsByUsername(String username);

    Optional<User> findUserByUsername(String username);

    List<User> findTop15ByNameContainingIgnoreCase(String query);
}
