package ripoff.facebook.clients.search;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("search")
public interface SearchClient {

    @PostMapping(path = "api/v1/search")
    void addUser(@RequestBody UserRequest userRequest);

    @DeleteMapping(path = "api/v1/search/{id}")
    void removeUser(@PathVariable("id") Long id);

    @PutMapping(path = "api/v1/search")
    void updateUser(@RequestBody UserRequest userRequest);
}
