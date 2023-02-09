package ripoff.facebook.authentication.authenticateUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/auth/session")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Authenticates session and returns user id.
     * @param session
     * @return
     */
    @PostMapping
    public Long authenticateTokenAndReturnUserId(@RequestParam String session) {
        log.info("Received token authentication request.");
        return service.authenticateSession(session);
    }
}
