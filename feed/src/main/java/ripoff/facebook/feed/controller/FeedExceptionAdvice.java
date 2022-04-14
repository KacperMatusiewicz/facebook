package ripoff.facebook.feed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ripoff.facebook.feed.application.FeedEmptyException;

@ControllerAdvice
public class FeedExceptionAdvice {
    @ExceptionHandler(FeedEmptyException.class)
    public ResponseEntity<String> handleBadUserDataException(FeedEmptyException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
