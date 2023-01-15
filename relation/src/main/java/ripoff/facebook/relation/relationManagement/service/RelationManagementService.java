package ripoff.facebook.relation.relationManagement.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ripoff.facebook.amqp.NotificationDTO;
import ripoff.facebook.amqp.NotificationType;
import ripoff.facebook.relation.commons.repository.GroupRepository;
import ripoff.facebook.relation.relationManagement.repository.RelationRequest;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestRepository;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestStatus;
import ripoff.facebook.relation.relationManagement.repository.RelationRequestType;

@Service
@AllArgsConstructor
public class RelationManagementService {

    GroupRepository repository;
    RelationRequestRepository relationRequestRepository;

    AmqpTemplate amqpTemplate;

    public void follow(Long followerId, Long targetId) {
        repository.createFollowRelation(followerId, targetId);
    }


    private void makeFriendship(Long friendId1, Long friendId2) {
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
        relationRequestRepository.deleteByRequesterIdAndRecipientId(friend1, friend2);
        relationRequestRepository.deleteByRequesterIdAndRecipientId(friend2, friend1);
        amqpTemplate.convertAndSend(
                "general-notification-queue",
                new NotificationDTO(
                        friend2,
                        "I'm not your friend anymore. Goodbye.",
                        friend1,
                        NotificationType.FRIEND_REMOVED
                )
        );
    }

    @Transactional
    public void createFriendRequest(FriendshipRequestDto friendshipRequest){
        if (
                relationRequestRepository
                        .existsByRequesterIdAndRecipientId(
                                friendshipRequest.getUserId(),
                                friendshipRequest.getTargetId()
                        )
        ) {
            throw new RequestExistsException("Request was sent before");
        }

        RelationRequest request = RelationRequest.builder()
                .requesterId(friendshipRequest.getUserId())
                .recipientId(friendshipRequest.getTargetId())
                .requestType(RelationRequestType.FRIEND)
                .requestStatus(RelationRequestStatus.PENDING)
                .build();
        relationRequestRepository.save(request);

        amqpTemplate.convertAndSend(
                "general-notification-queue",
                new NotificationDTO(
                        friendshipRequest.getTargetId(),
                        "You have been sent a friendship request",
                        friendshipRequest.getUserId(),
                        NotificationType.FRIEND_REQUEST
                )
        );
    }
    @Transactional
    public void respondToFriendshipRequest(FriendResponseDto friendResponseDto) {
        RelationRequest request = relationRequestRepository
                .findByRequesterIdAndRecipientId(friendResponseDto.getTargetId(), friendResponseDto.getUserId())
                .orElseThrow(
                        () -> new RequestNotFoundException(
                                "Request was not found. Requester and recipient ids: " +
                                friendResponseDto.getUserId() + ", " + friendResponseDto.getTargetId()
                        )
                );

        if(!friendResponseDto.getUserId().equals(request.getRecipientId())) {
            throw new UnauthorizedOperationException("User is not authorized to respond to this friendship request.");
        }

        switch (friendResponseDto.getResponseType()){
            case ACCEPT:{
                request.setRequestStatus(RelationRequestStatus.ACCEPTED);
                makeFriendship(request.getRequesterId(), request.getRecipientId());
                amqpTemplate.convertAndSend(
                        "general-notification-queue",
                        new NotificationDTO(
                                friendResponseDto.getTargetId(),
                                "You have been added as a friend",
                                friendResponseDto.getUserId(),
                                NotificationType.FRIEND_APPROVAL
                        )
                );
                break;
            }
            case IGNORE:{
                request.setRequestStatus(RelationRequestStatus.IGNORED);
                break;
            }
            default:
                throw new UnsupportedResponseTypeException(
                        "Response type: "+friendResponseDto.getResponseType()+ " is not supported."
                );
        }

        relationRequestRepository.save(request);
    }
}
