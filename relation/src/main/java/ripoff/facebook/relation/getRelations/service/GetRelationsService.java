package ripoff.facebook.relation.getRelations.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.relation.commons.repository.GroupRepository;
import ripoff.facebook.relation.commons.repository.User;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestRepository;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestStatus;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetRelationsService {

    private GroupRepository repository;
    private RelationRequestRepository requestRepository;


    public Set<Long> getFollowers(Long id) {
        return repository.getFollowersByUserId(id)
                .orElseThrow(
                        () -> new GroupNotFoundException("Followers not found.")
                )
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> getFollowings(Long id) {
        return repository.getFollowingsByUserId(id)
                .orElseThrow(
                        () -> new GroupNotFoundException("Followings not found.")
                )
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> getFriends(Long id) {
        return repository.getFriendsByUserId(id)
                .orElseThrow(
                        () -> new GroupNotFoundException("Friends not found.")
                )
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> getSentFriendsRequests(Long id) {
        return requestRepository.findAllByRequesterIdAndRequestStatus(id, RelationRequestStatus.PENDING)
                .stream()
                .map(relationRequest -> relationRequest.getRecipientId())
                .collect(Collectors.toSet());
    }

    public Set<Long> getReceivedFriendsRequests(Long id) {
        return requestRepository.findAllByRecipientIdAndRequestStatus(id, RelationRequestStatus.PENDING)
                .stream()
                .map(relationRequest -> relationRequest.getRequesterId())
                .collect(Collectors.toSet());
    }
}
