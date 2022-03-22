package ripoff.facebook.clients.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user")
public interface UserClient {

    @GetMapping(path = "api/v1/user/{userId}")
    UserExistsResponse checkIfUserExistsById(@PathVariable("userId") Long userId);

}
