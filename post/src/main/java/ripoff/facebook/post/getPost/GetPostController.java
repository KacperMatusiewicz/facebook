package ripoff.facebook.post.getPost;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.post.commons.repository.Post;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class GetPostController {

    private final GetPostService service;

    @GetMapping("{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return service.getAllPostsByUser(userId);
    }

    @GetMapping
    public List<Post> getPostsForLoggedUser(@RequestHeader("user-id") Long userId) {
        return service.getAllPostsByUser(userId);
    }

}
