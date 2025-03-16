package pet.project.userservice.exception;

public class WrongTokenType extends RuntimeException {

    public WrongTokenType(String message) {
        super(message);
    }
}
