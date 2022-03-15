package ripoff.facebook.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RelationService {

    RestTemplate restTemplate;

    public Set<Long> getGroupIds(Long visibilityGroupId) {
        return restTemplate.getForObject("localhost:8080/api/v1/relation/"+visibilityGroupId, HashSet.class);
    }
}
