package pet.project.userservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pet.project.userservice.exception.*;
import pet.project.userservice.model.dto.GeneralErrorResponse;

import static pet.project.userservice.constant.ErrorMessagesUtil.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralErrorResponse> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Unknown error")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<GeneralErrorResponse> handleUnknownException(UnknownException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Unknown exception")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GeneralErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .type("UserAlreadyExistsError")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralErrorResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .type("UserNotFoundError")
                        .message(exception.getMessage())
                        .details(USER_NOT_FOUND_DETAILS)
                        .build());
    }

    @ExceptionHandler(WrongTokenType.class)
    public ResponseEntity<GeneralErrorResponse> handleWrongTokenTypeException(WrongTokenType exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .type("WrongTokenTypeError")
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(AuthHeaderIsMissing.class)
    public ResponseEntity<GeneralErrorResponse> handleAuthHeaderIsMissingException(AuthHeaderIsMissing exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .type("AuthHeaderIsMissingError")
                        .message(exception.getMessage())
                        .build());
    }
}
