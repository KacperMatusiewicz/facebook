package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/feed/user")
@AllArgsConstructor
public class UserFeedQueueController {

    UserFeedQueueCreationService service;

    @PostMapping("{userId}")
    public void createUserQueue(@PathVariable Long userId) {
        service.createQueue(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteUserQueue(@PathVariable Long userId) {
        service.deleteQueue(userId);
    }
}
