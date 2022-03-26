package ripoff.facebook.clients.relation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("relation")
public interface RelationClient {

    @GetMapping(path = "api/v1/relation/default/{id}")
    GroupIdsResponse getFollowers(@PathVariable("id") Long id);
}
