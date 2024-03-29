package ripoff.facebook.post.getPost;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class GetPostController {

    private final GetPostService service;

    @GetMapping("id")
    public PostView getPostById(@RequestParam("postId") Long postId){
        log.info("Received request to get post with id: " + postId);
        return service.getPostById(postId);
    }
    @GetMapping("{userId}")
    public List<PostView> getPostsByUserId(@PathVariable Long userId) {
        log.info("Received request to get all posts with user id: " + userId);
        return service.getAllPostsByUser(userId);
    }

    @GetMapping
    public List<PostView> getPostsForLoggedUser(@RequestHeader("user-id") Long userId) {
        log.info("Received request to get all posts for a logged user with user id: " + userId);
        return service.getAllPostsByUser(userId);
    }

}
