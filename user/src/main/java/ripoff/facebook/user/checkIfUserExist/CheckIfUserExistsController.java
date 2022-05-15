package ripoff.facebook.user.checkIfUserExist;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.user.UserExistsResponse;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class CheckIfUserExistsController {

    private final CheckIfUserExistsService service;

    @GetMapping("{userId}")
    public UserExistsResponse checkIfUserExistsById(@PathVariable Long userId) {
        return new UserExistsResponse(service.checkIfUserExistsById(userId));
    }

}
