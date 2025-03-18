package pet.project.userservice.exception;

public class FriendshipNotFoundException extends RuntimeException {

    public FriendshipNotFoundException(String message) {
        super(message);
    }
}
