package ripoff.facebook.notification.service;

public class UserPreferencesNotFoundException extends RuntimeException{
    public UserPreferencesNotFoundException(String msg) {
        super(msg);
    }
}
