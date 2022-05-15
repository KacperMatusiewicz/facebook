package ripoff.facebook.post.deletePost;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String e) {
        super(e);
    }
}
