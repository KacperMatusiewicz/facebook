package ripoff.facebook.relation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.relation.GroupIdsResponse;
import ripoff.facebook.relation.entity.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RelationService {

    GroupRepository repository;


    public Set<Long> getFollowers(Long id){
        return repository.getFollowersByUserId(id)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group not found.")
                )
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }
}
