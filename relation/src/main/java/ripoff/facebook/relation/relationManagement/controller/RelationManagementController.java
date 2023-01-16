package ripoff.facebook.relation.relationManagement.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.relation.relationManagement.service.*;
@Slf4j
@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class RelationManagementController {

    RelationManagementService service;

    @PostMapping("follow")
    public void follow(@RequestHeader("user-id") Long userId, @RequestBody FollowRequest request) {
        log.info("Received request to follow user with id: " + request.getTargetId() + " by user id: " + userId);
        service.follow(userId, request.getTargetId());
    }

    @DeleteMapping("follow")
    public void unfollowUser(@RequestHeader("user-id") Long userId, @RequestParam Long targetId) {
        log.info("Received request to unfollow user with id: " + targetId + " by user id: " + userId);
        service.unfollow(userId, targetId);
    }

    @PostMapping("friend/request")
    @Transactional
    public void sendFriendRequest(@RequestHeader("user-id") Long userId, @RequestBody FriendshipRequest request) {
        log.info("Received request to friend user with id: " + request.getTargetId() + " by user id: " + userId);
        service.createFriendRequest(new FriendshipRequestDto(userId, request.getTargetId()));
    }

    @PostMapping("friend/response")
    public void sendFriendResponse(@RequestHeader("user-id") Long userId, @RequestBody FriendResponseRequest request) {
        log.info(
                "Received request to respond to friend request user with id: " + request.getTargetId() +
                " by user id: " + userId + " with response: " + request.responseType
        );
        service.respondToFriendshipRequest(new FriendResponseDto(userId, request.getTargetId(), request.getResponseType()));
    }

    @DeleteMapping("friend")
    public void unfriend(@RequestHeader("user-id") Long userId, @RequestParam Long targetId) {
        log.info("Received request to unfriend user with id: " + targetId + " by user id: " + userId);
        service.unfriend(userId, targetId);
    }
}
