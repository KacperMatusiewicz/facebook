package ripoff.facebook.relation.getRelations.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ripoff.facebook.relation.getRelations.service.GroupNotFoundException;

@ControllerAdvice
public class RelationExceptionsAdvice {

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<String> handleBadUserDataException(GroupNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
