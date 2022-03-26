package ripoff.facebook.relation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.clients.relation.GroupIdsResponse;
import ripoff.facebook.relation.entity.User;

import java.util.Set;

@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class RelationController {

    RelationService service;

    @GetMapping("/default/{id}")
    public GroupIdsResponse getFollowers(@PathVariable("id") Long id){
        return new GroupIdsResponse(service.getFollowers(id));
    }

}
