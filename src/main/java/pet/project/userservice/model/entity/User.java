package pet.project.userservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private long id;

    @NotNull(message = "username can't be null")
    private String username;

    @NotNull(message = "email can't be null")
    private String email;

    @NotNull(message = "password_hash can't be null")
    private String password_hash;

    @NotNull(message = "name can't be null")
    private String name;

    private String bio;

    private String avatar;

    @NotNull(message = "created_at can't be null")
    private Instant created_at;
}
