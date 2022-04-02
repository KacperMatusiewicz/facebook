package ripoff.facebook.clients.post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("post")
public interface PostClient {

    @DeleteMapping(path = "api/v1/post/{userId}")
    void deleteAllPostsByUserId(@PathVariable("userId") Long userId);
}
