package pet.project.userservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pet.project.userservice.exception.UnknownException;
import pet.project.userservice.exception.UserAlreadyExistsException;
import pet.project.userservice.model.dto.GeneralErrorResponse;

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
    public ResponseEntity<GeneralErrorResponse> handleUserNotFoundException(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(GeneralErrorResponse.builder()
                        .status(HttpStatus.CONFLICT.value())
                        .type("UserAlreadyExistsError")
                        .message(exception.getMessage())
                        .build());
    }
}
