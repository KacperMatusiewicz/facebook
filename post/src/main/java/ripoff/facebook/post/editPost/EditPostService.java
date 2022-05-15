package ripoff.facebook.post.editPost;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.commons.repository.PostRepository;
import ripoff.facebook.post.deletePost.PostNotFoundException;
import ripoff.facebook.post.deletePost.UnauthorizedPostOperationException;

@Service
@RequiredArgsConstructor
public class EditPostService {

    private final PostRepository repository;
    public Post updatePost(EditPostDto editPostDto) {
        Post post = getPost(editPostDto);
        authorizeUpdateOperation(editPostDto, post);
        return saveUpdatedPost(editPostDto, post);
    }

    private Post saveUpdatedPost(EditPostDto editPostDto, Post post) {
        return repository.save(
                new Post(
                        editPostDto.getPostId(),
                        editPostDto.getUserId(),
                        editPostDto.getContent(),
                        post.getAttachmentPath(),
                        post.getCreationDate()
                )
        );
    }

    private void authorizeUpdateOperation(EditPostDto editPostDto, Post post) {
        if(!post.getUserId().equals(editPostDto.getUserId())){
            throw new UnauthorizedPostOperationException("Unauthorized operation of post edition.");
        }
    }

    private Post getPost(EditPostDto editPostDto) {
        return repository
                .findById(editPostDto.getPostId())
                .orElseThrow(
                        () -> new PostNotFoundException("Edited post not found.")
                );
    }
}
