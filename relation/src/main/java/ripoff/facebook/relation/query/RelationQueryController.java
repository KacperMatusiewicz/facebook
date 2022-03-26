package ripoff.facebook.relation.query;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.clients.relation.GroupIdsResponse;

@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class RelationQueryController {

    RelationQueryService service;

    @GetMapping("/default/{id}")
    public GroupIdsResponse getFollowers(@PathVariable("id") Long id){
        return new GroupIdsResponse(service.getFollowers(id));
    }

}
