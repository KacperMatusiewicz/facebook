package ripoff.facebook.post.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ripoff.facebook.clients.relation.RelationClient;

import java.util.Set;

@Service
@AllArgsConstructor
public class RelationService {

    RestTemplate restTemplate;
    RelationClient relationClient;

    public Set<Long> getFollowers(Long visibilityGroupId) {
        return relationClient.getFollowers(visibilityGroupId).getGroupIds();
    }
}
