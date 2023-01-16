package ripoff.facebook.relation.getRelations.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.clients.relation.GroupIdsResponse;
import ripoff.facebook.relation.getRelations.service.GetRelationsService;
@Slf4j
@RestController
@RequestMapping("api/v1/relation")
@AllArgsConstructor
public class GetRelationsController {

    GetRelationsService service;

    @GetMapping("/followers/{id}")
    public GroupIdsResponse getFollowers(@PathVariable("id") Long id) {
        log.info("Received request to get all followers for user id: " + id);
        return new GroupIdsResponse(service.getFollowers(id));
    }
    @GetMapping("/followers/self")
    public GroupIdsResponse getOwnFollowers(@RequestHeader("user-id") Long id) {
        log.info("Received request to get all followers for logged user with id: " + id);
        return new GroupIdsResponse(service.getFollowers(id));
    }

    @GetMapping("/followings/self")
    public GroupIdsResponse getOwnFollowings(@RequestHeader("user-id") Long id) {
        log.info("Received request to get all followings for logged user with id: " + id);
        return new GroupIdsResponse(service.getFollowings(id));
    }
    @GetMapping("/friends/{id}")
    public GroupIdsResponse getFriends(@PathVariable("id") Long id) {
        log.info("Received request to get all friends for user id: " + id);
        return new GroupIdsResponse(service.getFriends(id));
    }
    @GetMapping("/friends/self")
    public GroupIdsResponse getOwnFriends(@RequestHeader("user-id") Long id) {
        log.info("Received request to get all friends for logged user with id: " + id);
        return new GroupIdsResponse(service.getFriends(id));
    }
    @GetMapping("/requests/sent/self")
    public GroupIdsResponse getOwnSentFriendsRequests(@RequestHeader("user-id") Long id) {
        log.info("Received request to get all sent friend requests for logged user with id: " + id);
        return new GroupIdsResponse(service.getSentFriendsRequests(id));
    }
    @GetMapping("/requests/received/self")
    public GroupIdsResponse getOwnReceivedFriendsRequests(@RequestHeader("user-id") Long id) {
        log.info("Received request to get all received friend requests for logged user with id: " + id);
        return new GroupIdsResponse(service.getReceivedFriendsRequests(id));
    }

}
