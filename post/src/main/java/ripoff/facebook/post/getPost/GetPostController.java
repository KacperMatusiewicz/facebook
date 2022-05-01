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
        //return service.getAllPostsByUser(userId);

        return List.of(
                new Post(1L , 2L, "jakis to jest post", "", LocalDateTime.now()),
                new Post(2L , 2L, "jakis to jest inny post", "", LocalDateTime.now()),
                new Post(3L , 2L, "jakis to jest post jeszcze innny i dłuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu   uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu uuuuuuuuuuuuuuuu uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuugi post", "", LocalDateTime.now())
        );
    }

}
