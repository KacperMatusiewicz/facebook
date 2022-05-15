package ripoff.facebook.user.getUserDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class GetUserDetailsController {

    private final GetUserDetailsService service;

    @GetMapping("details")
    public UserDetailsDto getUserDetails(@RequestHeader("user-id") Long userId) {
        return service.getUserDetails(userId);
    }

    @GetMapping("details/{userId}")
    public UserDetailsDto getUserDetailsById(@PathVariable Long userId) {
        return service.getUserDetails(userId);
    }
}
