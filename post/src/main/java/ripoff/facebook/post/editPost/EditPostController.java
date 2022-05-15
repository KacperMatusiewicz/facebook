package ripoff.facebook.post.editPost;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.createPost.controller.PostCreationRequest;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;

@RestController
@RequestMapping("api/v1/post/edit")
@RequiredArgsConstructor
public class EditPostController {
    private final EditPostService service;

    @PutMapping
    public Post updatePost(@RequestHeader("user-id") Long userId, @RequestBody UpdatePostRequest request) {
        return service.updatePost(
                new EditPostDto(
                        request.getPostId(),
                        userId,
                        request.getContent()
                )
        );
    }
}
