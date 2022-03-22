package ripoff.facebook.notification.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotificationExceptionsAdvice {

    @ExceptionHandler(UserPreferencesNotFoundException.class)
    public ResponseEntity<String> handleBadUserDataException(UserPreferencesNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
