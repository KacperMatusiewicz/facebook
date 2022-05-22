package ripoff.facebook.relation.relationManagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.relation.relationManagement.service.*;

@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class RelationManagementController {

    RelationManagementService service;

    @PostMapping("follow")
    public void follow(@RequestHeader("user-id") Long userId, @RequestBody FollowRequest request) {
        service.follow(userId, request.getTargetId());
    }

    @DeleteMapping("follow")
    public void unfollowUser(@RequestHeader("user-id") Long userId, @RequestParam Long targetId) {
        service.unfollow(userId, targetId);
    }

    @PostMapping("friend/request")
    @Transactional
    public void sendFriendRequest(@RequestHeader("user-id") Long userId, @RequestBody FriendshipRequest request) {
        service.createFriendRequest(new FriendshipRequestDto(userId, request.getTargetId()));
    }

    @PostMapping("friend/response")
    public void sendFriendResponse(@RequestHeader("user-id") Long userId, @RequestBody FriendResponseRequest request) {
        service.respondToFriendshipRequest(new FriendResponseDto(userId, request.getTargetId(), request.getResponseType()));
    }

    @DeleteMapping("friend")
    public void unfriend(@RequestHeader("user-id") Long userId, @RequestParam Long targetId) {
        service.unfriend(userId, targetId);
    }
}
