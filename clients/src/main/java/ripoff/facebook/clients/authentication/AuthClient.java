package ripoff.facebook.clients.authentication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("authentication")
public interface AuthClient {

    @PostMapping(path = "api/v1/auth/register")
    void registerNewUserAuthenticationMethod(@RequestBody AuthenticationMethodDto authenticationMethodDto);

    @DeleteMapping(path = "api/v1/auth/delete/{userId}")
    void deleteUserAuthenticationData(@PathVariable(name = "userId") Long userId);

}
