package ripoff.facebook.post.editPost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.post.commons.repository.Post;
import ripoff.facebook.post.createPost.controller.PostCreationRequest;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;
@Slf4j
@RestController
@RequestMapping("api/v1/post/edit")
@RequiredArgsConstructor
public class EditPostController {
    private final EditPostService service;

    @PutMapping
    public Post updatePost(@RequestHeader("user-id") Long userId, @RequestBody UpdatePostRequest request) {
        log.info("Received request to edit post with user id: " + userId + " and post id: " + request.getPostId());
        return service.updatePost(
                new EditPostDto(
                        request.getPostId(),
                        userId,
                        request.getContent()
                )
        );
    }
}
