package ripoff.facebook.post.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ripoff.facebook.post.exceptions.BadPostDataException;

@ControllerAdvice
public class PostExceptionsAdvice {

    @ExceptionHandler(BadPostDataException.class)
    public ResponseEntity<String> handleBadUserDataException(BadPostDataException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}
