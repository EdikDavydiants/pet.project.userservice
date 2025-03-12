package pet.project.userservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
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

    public User(long id, String username, String email, @Size(min = 60, max = 60) String passwordHash, String name, String bio, String avatar, Instant createdAt, List<Friendship> invitedByUser, List<Friendship> invitationsToUser) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.bio = bio;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.invitedByUser = invitedByUser;
        this.invitationsToUser = invitationsToUser;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public @Size(min = 60, max = 60) String getPasswordHash() {
        return this.passwordHash;
    }

    public String getName() {
        return this.name;
    }

    public String getBio() {
        return this.bio;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public List<Friendship> getInvitedByUser() {
        return this.invitedByUser;
    }

    public List<Friendship> getInvitationsToUser() {
        return this.invitationsToUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(@Size(min = 60, max = 60) String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setInvitedByUser(List<Friendship> invitedByUser) {
        this.invitedByUser = invitedByUser;
    }

    public void setInvitationsToUser(List<Friendship> invitationsToUser) {
        this.invitationsToUser = invitationsToUser;
    }

    public static class UserBuilder {
        private long id;
        private String username;
        private String email;
        private @Size(min = 60, max = 60) String passwordHash;
        private String name;
        private String bio;
        private String avatar;
        private Instant createdAt;
        private List<Friendship> invitedByUser;
        private List<Friendship> invitationsToUser;

        UserBuilder() {
        }

        public UserBuilder id(long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder passwordHash(@Size(min = 60, max = 60) String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public UserBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UserBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder invitedByUser(List<Friendship> invitedByUser) {
            this.invitedByUser = invitedByUser;
            return this;
        }

        public UserBuilder invitationsToUser(List<Friendship> invitationsToUser) {
            this.invitationsToUser = invitationsToUser;
            return this;
        }

        public User build() {
            return new User(this.id, this.username, this.email, this.passwordHash, this.name, this.bio, this.avatar, this.createdAt, this.invitedByUser, this.invitationsToUser);
        }

        public String toString() {
            return "User.UserBuilder(id=" + this.id + ", username=" + this.username + ", email=" + this.email + ", password=" + this.passwordHash + ", name=" + this.name + ", bio=" + this.bio + ", avatar=" + this.avatar + ", createdAt=" + this.createdAt + ", invitedByUser=" + this.invitedByUser + ", invitationsToUser=" + this.invitationsToUser + ")";
        }
    }
}
