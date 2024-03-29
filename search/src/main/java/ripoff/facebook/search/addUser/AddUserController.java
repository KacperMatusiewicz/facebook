package ripoff.facebook.search.addUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.search.UserRequest;
import ripoff.facebook.search.User;

@Slf4j
@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class AddUserController {
    private final AddUserService service;

    @PostMapping
    public void addUser(@RequestBody UserRequest user){
        log.info("Received request to add user with id: " + user.getId());
        service.addUser(new User(user.getId(), user.getName(), user.getLastName()));
    }
}
