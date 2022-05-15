package ripoff.facebook.post.deletePost;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class DeletePostController {
    private final DeletePostService service;

    @DeleteMapping("all/{userId}")
    public void deleteAllPostsByUserId(@PathVariable Long userId) {
        service.deleteAllPostsByUserId(userId);
    }

    @DeleteMapping("{postId}")
    public void deletePostById(@RequestHeader("user-id") Long userId, @PathVariable Long postId) {
        service.deletePostById(userId, postId);
    }

}
