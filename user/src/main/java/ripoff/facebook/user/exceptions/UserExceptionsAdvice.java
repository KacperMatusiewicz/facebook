package ripoff.facebook.user.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionsAdvice {

    @ExceptionHandler
    public ResponseEntity<Object> handleBadUserDataException() {
        return null;
    }
}
