package ripoff.facebook.post.getPost;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.post.commons.repository.Post;

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
}
