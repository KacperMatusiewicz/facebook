package ripoff.facebook.feed.application;

public class FeedEmptyException extends RuntimeException {
    public FeedEmptyException(String message) {
        super(message);
    }
}
