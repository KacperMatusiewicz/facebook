package ripoff.facebook.authentication.loginUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.ws.rs.HeaderParam;
@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("login")
    public ResponseEntity loginUser(@RequestBody UserCredentials userCredentials){

        log.info("Received Login request.");

        String sessionId = loginService.loginUser(userCredentials);
        int maxAge = 7*24*60*60;

        ResponseCookie jsessionid = ResponseCookie.from("JSESSIONID", sessionId)
        .maxAge(maxAge)
        .path("/")
        .httpOnly(true)
        .build();

        ResponseCookie applicationAuth = ResponseCookie.from("applicationAuth", "logged")
        .maxAge(maxAge)
        .path("/")
        .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, applicationAuth.toString())
                .header(HttpHeaders.SET_COOKIE, jsessionid.toString())
                .build();
    }

    @PostMapping("logout")
    public ResponseEntity logoutUser(@CookieValue(value = "JSESSIONID") String sessionId) {

        log.info("Received logout request.");

        loginService.logoutUser(sessionId);
        ResponseCookie jsessionid = ResponseCookie.from("JSESSIONID", "")
                .maxAge(0)
                .path("/")
                .httpOnly(true)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jsessionid.toString())
                .build();
    }

    @PostMapping("logoutAll")
    public ResponseEntity logoutFromAll(@RequestHeader("user-id") Long userId) {

        log.info("Received logout from all request.");

        loginService.logoutUserFromAllSessions(userId);
        ResponseCookie jsessionid = ResponseCookie.from("JSESSIONID", "")
                .maxAge(0)
                .path("/")
                .httpOnly(true)
                .build();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, jsessionid.toString())
                .build();
    }
}
