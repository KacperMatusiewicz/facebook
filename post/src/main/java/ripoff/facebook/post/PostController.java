package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.post.dto.PostCreationRequest;
import ripoff.facebook.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
public class PostController {
    PostService postService;

    @PostMapping
    public void createPost(@RequestBody PostCreationRequest request) {
        postService.createPost(request);
    }

    @GetMapping("{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId){
        return postService.getAllPostsByUser(userId);
    }

}
