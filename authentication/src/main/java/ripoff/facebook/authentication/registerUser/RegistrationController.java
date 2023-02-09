package ripoff.facebook.authentication.registerUser;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.clients.authentication.AuthenticationMethodDto;

@RestController
@RequestMapping("api/v1/auth/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService service;

    /**
     * POST api/v1/auth/register <br>
     * Creates user authentication data
     * @param authenticationMethodDto json body containg id: number, email:string, password:string
     */
    @PostMapping
    public void registerNewUserAuthenticationMethod(@RequestBody AuthenticationMethodDto authenticationMethodDto) {
        service.registerNewUserAuthenticationMethod(authenticationMethodDto);
    }
}
