package ripoff.facebook.post.deletePost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class DeletePostService {
    private final PostRepository repository;

    @Transactional
    public void deleteAllPostsByUserId(Long userId) {
        repository.deleteAllByUserId(userId);
    }

    public void deletePostById(Long userId, Long postId) {
        Post post = getPost(postId);
        authorizeDeleteOperation(userId, post);
        repository.deleteById(postId);
    }

    private void authorizeDeleteOperation(Long userId, Post post) {
        if(!post.getUserId().equals(userId)) {
            throw new UnauthorizedPostDeletionException("There is no permission for deleting this post.");
        }
    }

    private Post getPost(Long postId) {
        return repository
                .findById(postId)
                .orElseThrow(
                () -> new PostNotFoundException("Post with this id does not exist.")
        );
    }
}
