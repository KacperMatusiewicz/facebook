package ripoff.facebook.post.createPost.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.post.createPost.service.CreatePostService;
import ripoff.facebook.post.createPost.service.dto.PostCreationDto;

import javax.ws.rs.HeaderParam;

@RestController
@RequestMapping("api/v1/post")
@AllArgsConstructor
public class CreatePostController {

    private final CreatePostService postService;

    @PostMapping
    public void createPost(@RequestHeader("user-id") Long userId, @RequestBody PostCreationRequest request) {
        postService.createPost(
                new PostCreationDto(
                        userId,
                        request.getContent(),
                        request.getVisibilityGroupType(),
                        request.getVisibilityUsersId()
                )
        );
    }
}
