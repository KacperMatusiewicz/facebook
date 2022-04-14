package ripoff.facebook.feed.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.feed.repository.UserFeedPost;
import ripoff.facebook.feed.application.UserFeedService;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedController {

    private UserFeedService service;

    @GetMapping("{userId}")
    public List<UserFeedPost> getAllPostById(@PathVariable Long userId) {
        return service.getAllPostsByUserId(userId);
    }

}
