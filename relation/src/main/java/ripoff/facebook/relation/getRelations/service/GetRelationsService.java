package ripoff.facebook.relation.getRelations.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.commons.repository.GroupRepository;
import ripoff.facebook.relation.commons.repository.User;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetRelationsService {

    GroupRepository repository;


    public Set<Long> getFollowers(Long id) {
        return repository.getFollowersByUserId(id)
                .orElseThrow(
                        () -> new GroupNotFoundException("Group not found.")
                )
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }
}
