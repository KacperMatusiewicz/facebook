package ripoff.facebook.post.createPost.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.clients.relation.RelationClient;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final RelationClient relationClient;

    public Set<Long> getFollowers(Long visibilityGroupId) {
        return relationClient.getFollowers(visibilityGroupId).getGroupIds();
    }

    public Set<Long> getFriends(Long visibilityGroupId) {
        return relationClient.getFriends(visibilityGroupId).getGroupIds();
    }
}
