package ripoff.facebook.relation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.entity.Group;
import ripoff.facebook.relation.entity.GroupUser;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RelationService {

    GroupRepository repository;

    public Set<GroupUser> getUsersByGroupId(Long groupId) {
        return repository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException("Group not found."))
                .getUsers();
    }

    public Long createNewGroup(CreateNewGroupRequest request) {

        Set<GroupUser> groupUsers =  request.getUserIds().stream()
                .map(
                        (userId) -> GroupUser.builder()
                                .userId(userId)
                                .build()
                )
                .collect(Collectors.toSet());

        Group group = Group.builder()
                .users(groupUsers)
                .build();

        return repository.save(group).getGroupId();
    }
}
