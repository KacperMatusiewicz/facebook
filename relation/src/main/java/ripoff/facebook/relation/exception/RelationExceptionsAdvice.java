package ripoff.facebook.relation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RelationExceptionsAdvice {

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> handleBadUserDataException(GroupNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
