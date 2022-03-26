package ripoff.facebook.relation.query;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.exception.GroupNotFoundException;
import ripoff.facebook.relation.repository.GroupRepository;
import ripoff.facebook.relation.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RelationQueryService {

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
