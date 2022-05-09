package ripoff.facebook.authentication.commons;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String e) {
        super(e);
    }
}
