package pet.project.userservice.model.dto.response;

import jakarta.validation.constraints.NotNull;

public record AuthenticationDtoResponse(

        @NotNull
        Long id,

        @NotNull
        String passwordHash
) {
    public static AuthenticationDtoResponseBuilder builder() {
        return new AuthenticationDtoResponseBuilder();
    }

    public static class AuthenticationDtoResponseBuilder {
        private @NotNull Long id;
        private @NotNull String passwordHash;

        AuthenticationDtoResponseBuilder() {
        }

        public AuthenticationDtoResponseBuilder id(@NotNull Long id) {
            this.id = id;
            return this;
        }

        public AuthenticationDtoResponseBuilder passwordHash(@NotNull String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public AuthenticationDtoResponse build() {
            return new AuthenticationDtoResponse(this.id, this.passwordHash);
        }

        public String toString() {
            return "AuthenticationDtoResponse.AuthenticationDtoResponseBuilder(id=" + this.id + ", passwordHash=" + this.passwordHash + ")";
        }
    }
}
