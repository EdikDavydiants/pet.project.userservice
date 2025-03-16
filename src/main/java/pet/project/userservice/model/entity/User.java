package pet.project.userservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 60, max = 60)
    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String name;

    private String bio;

    private String avatar;

    @Column(nullable = false)
    private Instant createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friendship> invitedByUser;

    @OneToMany(mappedBy = "friend", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Friendship> invitationsToUser;
}
