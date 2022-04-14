package ripoff.facebook.post.createPost.service;

public class BadPostDataException extends RuntimeException {
    public BadPostDataException(String msg) {
        super(msg);
    }
}
