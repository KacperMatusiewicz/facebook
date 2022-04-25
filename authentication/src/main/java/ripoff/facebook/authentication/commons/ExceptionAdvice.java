package ripoff.facebook.authentication.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ripoff.facebook.authentication.authenticateUser.InvalidSessionException;
import ripoff.facebook.authentication.loginUser.InvalidCredentialsException;
import ripoff.facebook.authentication.loginUser.LoginNotFoundException;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(InvalidSessionException.class)
    public ResponseEntity<String> handleBadUserDataException(InvalidSessionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LoginNotFoundException.class)
    public ResponseEntity<String> handleLoginNotFoundException(LoginNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
