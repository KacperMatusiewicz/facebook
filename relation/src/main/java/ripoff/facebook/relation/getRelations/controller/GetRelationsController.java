package ripoff.facebook.relation.getRelations.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.relation.GroupIdsResponse;
import ripoff.facebook.relation.getRelations.service.GetRelationsService;

@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class GetRelationsController {

    GetRelationsService service;

    @GetMapping("/followers/{id}")
    public GroupIdsResponse getFollowers(@PathVariable("id") Long id) {
        return new GroupIdsResponse(service.getFollowers(id));
    }
    @GetMapping("/friends/{id}")
    public GroupIdsResponse getFriends(@PathVariable("id") Long id) {
        return new GroupIdsResponse(service.getFriends(id));
    }

}
