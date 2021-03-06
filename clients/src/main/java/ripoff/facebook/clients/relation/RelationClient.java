package ripoff.facebook.clients.relation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("relation")
public interface RelationClient {

    @GetMapping(path = "api/v1/relation/followers/{id}")
    GroupIdsResponse getFollowers(@PathVariable("id") Long id);

    @GetMapping(path = "api/v1/relation/friends/{id}")
    GroupIdsResponse getFriends(@PathVariable("id") Long id);

    @PostMapping(path = "api/v1/relation/user/{id}")
    void createUser(@PathVariable("id") Long id);

    @DeleteMapping(path = "api/v1/relation/user/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
