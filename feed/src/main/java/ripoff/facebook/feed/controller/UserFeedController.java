package ripoff.facebook.feed.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.feed.application.UserFeedService;

import java.util.List;

@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedController {

    private UserFeedService service;

    @GetMapping
    public List<String> getAllPostById(@RequestHeader("user-id") Long userId) {
        return service.getAllPostsByUserId(userId);
    }

}
