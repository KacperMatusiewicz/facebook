package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedController {

    UserFeedConsumerService service;

    @GetMapping("{userId}")
    public PostIdsList getPostsByUserId(@PathVariable Long userId, @RequestParam(defaultValue = "10", required = false) Integer amount) {
        return service.getFeedPosts(userId, amount);
    }
}
