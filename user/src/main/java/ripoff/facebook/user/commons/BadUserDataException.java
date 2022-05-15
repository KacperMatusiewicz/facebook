package ripoff.facebook.user.commons;

public class BadUserDataException extends RuntimeException {
    public BadUserDataException(String message) {
        super(message);
    }
}
