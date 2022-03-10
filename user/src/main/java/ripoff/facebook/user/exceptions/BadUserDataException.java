package ripoff.facebook.user.exceptions;

public class BadUserDataException extends RuntimeException {
    public BadUserDataException(String message) {
        super(message);
    }
}
