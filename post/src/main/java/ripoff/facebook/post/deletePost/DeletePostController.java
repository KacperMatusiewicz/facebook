package ripoff.facebook.post.deletePost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class DeletePostController {
    private final DeletePostService service;

    @DeleteMapping("all/{userId}")
    public void deleteAllPostsByUserId(@PathVariable Long userId) {
        log.info("Received request to delete all posts with user id: " + userId);
        service.deleteAllPostsByUserId(userId);
    }

    @DeleteMapping("{postId}")
    public void deletePostById(@RequestHeader("user-id") Long userId, @PathVariable Long postId) {
        log.info("Received request to delete post with user id: " + userId + " and post id: " + postId);
        service.deletePostById(userId, postId);
    }

}
