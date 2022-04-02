package ripoff.facebook.clients.feed;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("feed")
public interface FeedUserClient {

    @PostMapping(path = "api/v1/feed/user/{userId}")
    void createUserQueue(@PathVariable("userId") Long userId);

}
