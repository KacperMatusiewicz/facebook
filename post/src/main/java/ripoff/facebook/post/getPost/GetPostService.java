package ripoff.facebook.post.getPost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;
import ripoff.facebook.post.deletePost.PostNotFoundException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPostService {

    private final PostRepository repository;

    public List<Post> getAllPostsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return repository.findById(postId)
                .orElseThrow(
                        () -> new PostNotFoundException("Post with id: "+ postId + " doesn't exist.")
                );
    }
}
