package ripoff.facebook.user.getUserDetails;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class GetUserDetailsController {

    private final GetUserDetailsService service;

    @GetMapping("details")
    public UserDetailsDto getUserDetails(@RequestHeader("user-id") Long userId) {
        log.info("Received request to get logged user details with id: " + userId);
        return service.getUserDetails(userId);
    }

    @GetMapping("details/{userId}")
    public UserDetailsDto getUserDetailsById(@PathVariable Long userId) {
        log.info("Received request to get user details with id: " + userId);
        return service.getUserDetails(userId);
    }
}
