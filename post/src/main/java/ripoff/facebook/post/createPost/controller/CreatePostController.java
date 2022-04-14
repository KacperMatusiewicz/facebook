package ripoff.facebook.post.createPost.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.post.createPost.service.CreatePostService;
import ripoff.facebook.post.createPost.service.dto.PostCreationRequest;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
public class CreatePostController {

    private final CreatePostService postService;

    @PostMapping
    public void createPost(@RequestBody PostCreationRequest request) {
        postService.createPost(request);
    }
}
