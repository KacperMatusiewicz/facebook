package ripoff.facebook.feed.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.feed.application.UserFeedService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/feed")
@AllArgsConstructor
public class UserFeedController {

    private UserFeedService service;

    @GetMapping
    public List<String> getAllPostById(@RequestHeader("user-id") Long userId) {
        log.info("Received get all user's feed posts request.");
        return service.getAllPostsByUserId(userId);
    }

}
