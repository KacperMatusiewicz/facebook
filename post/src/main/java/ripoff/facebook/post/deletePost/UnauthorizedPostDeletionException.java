package ripoff.facebook.post.deletePost;

public class UnauthorizedPostDeletionException extends RuntimeException {
    public UnauthorizedPostDeletionException(String e) {
        super(e);
    }
}
