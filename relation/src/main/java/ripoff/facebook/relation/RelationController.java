package ripoff.facebook.relation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.relation.entity.GroupUser;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class RelationController {

    RelationService service;

    @GetMapping
    public Set<GroupUser> getUsersByGroupId(@PathVariable Long groupId) {
        return service.getUsersByGroupId(groupId);
    }

    @PostMapping
    public Long addNewGroup(@RequestBody CreateNewGroupRequest request) {
        return service.createNewGroup(request);
    }
}
