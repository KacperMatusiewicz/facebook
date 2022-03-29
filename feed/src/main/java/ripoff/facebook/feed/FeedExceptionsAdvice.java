package ripoff.facebook.feed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeedExceptionsAdvice {

    @ExceptionHandler(FeedEmptyException.class)
    public ResponseEntity<String> handleFeedEmptyException(FeedEmptyException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
    }
}
