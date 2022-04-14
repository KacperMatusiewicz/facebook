package ripoff.facebook.post.deletePost;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class DeletePostController {
    private final DeletePostService service;

    @DeleteMapping("{userId}")
    public void deleteAllPostsByUserId(@PathVariable Long userId) {
        service.deleteAllPostsByUserId(userId);
    }

}
