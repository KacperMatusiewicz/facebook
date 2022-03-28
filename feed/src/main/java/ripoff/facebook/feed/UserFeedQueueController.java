package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedQueueController {

    UserFeedQueueCreationService service;

    @PostMapping("{userId}")
    public void createUserQueue(@PathVariable Long userId) {
        service.createQueue(userId);
    }
}
