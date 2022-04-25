package ripoff.facebook;

public class BadAuthorizationFormat extends RuntimeException {
    public BadAuthorizationFormat(String e) {
        super(e);
    }
}
