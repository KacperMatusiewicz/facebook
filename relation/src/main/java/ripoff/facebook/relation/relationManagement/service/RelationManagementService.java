package ripoff.facebook.relation.relationManagement.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ripoff.facebook.relation.commons.repository.GroupRepository;

@Service
@AllArgsConstructor
public class RelationManagementService {

    GroupRepository repository;

    public void follow(Long followerId, Long targetId) {
        repository.createFollowRelation(followerId, targetId);
    }

    @Transactional
    public void makeFriendship(Long friendId1, Long friendId2) {
        repository.createFollowRelation(friendId1, friendId2);
        repository.createFollowRelation(friendId2, friendId1);
    }

    public void unfollow(Long followerId, Long targetId) {
        repository.deleteFollowRelation(followerId, targetId);
    }

    @Transactional
    public void unfriend(Long friend1, Long friend2) {
        repository.deleteFollowRelation(friend1, friend2);
        repository.deleteFollowRelation(friend2, friend1);
    }
}
