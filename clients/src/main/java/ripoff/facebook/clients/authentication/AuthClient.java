package ripoff.facebook.clients.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("authentication")
public interface AuthClient {

    @PostMapping(path = "api/v1/auth/register")
    void registerNewUserAuthenticationMethod(@RequestBody AuthenticationMethodDto authenticationMethodDto);

}
