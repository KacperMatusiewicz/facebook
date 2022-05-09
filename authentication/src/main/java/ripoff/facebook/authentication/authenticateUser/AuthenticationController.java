package ripoff.facebook.authentication.authenticateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequestMapping("api/v1/auth/session")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping
    public Long authenticateToken(@RequestParam String session) {
        return service.authenticateSession(session);
    }
}
