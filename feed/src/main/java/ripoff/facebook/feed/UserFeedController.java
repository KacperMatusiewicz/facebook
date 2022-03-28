package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedController {

    UserFeedConsumerService service;

    @GetMapping("{userId}")
    public PostIdsList getPostsByUserId(@PathVariable Long userId) {
        return service.getFeedPosts(userId);
    }
}
