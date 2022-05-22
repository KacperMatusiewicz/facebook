package ripoff.facebook.relation.relationManagement.service;

public class UnauthorizedOperationException extends RuntimeException {
    public UnauthorizedOperationException(String e) {
        super(e);
    }
}
