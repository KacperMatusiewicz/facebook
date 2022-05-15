package ripoff.facebook.post.deletePost;

public class UnauthorizedPostOperationException extends RuntimeException {
    public UnauthorizedPostOperationException(String e) {
        super(e);
    }
}
