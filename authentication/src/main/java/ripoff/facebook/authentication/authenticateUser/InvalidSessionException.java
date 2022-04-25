package ripoff.facebook.authentication.authenticateUser;

public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException(String e) {
        super(e);
    }
}
