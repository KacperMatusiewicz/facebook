package ripoff.facebook.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionsAdvice {

    @ExceptionHandler(BadUserDataException.class)
    public ResponseEntity<String> handleBadUserDataException(BadUserDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<String> handleEmailExistsException(EmailExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ActivationLinkNotFound.class)
    public ResponseEntity<String> handleActivationLinkNotFoundException(ActivationLinkNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
