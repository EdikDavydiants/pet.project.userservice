package pet.project.userservice.exception;

public class AuthHeaderIsMissing extends RuntimeException {

    public AuthHeaderIsMissing(String message) {
        super(message);
    }
}
