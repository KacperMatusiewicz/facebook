package ripoff.facebook.user.updateUser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserService service;

    @PutMapping("personal")
    public void changePersonalInfo(@RequestHeader("user-id") Long userId, @RequestBody PersonalInfoRequest personalInfoRequest){
        service.changePersonalInfo(
                new PersonalInfoDto(
                        userId,
                        personalInfoRequest.getName(),
                        personalInfoRequest.getLastName()
                )
        );
    }

    @PutMapping("contact")
    public void changeContactInfo(@RequestHeader("user-id") Long userId, @RequestBody ContactInfoRequest contactInfoRequest){
        service.changeContactInfo(
                new ContactInfoDto(
                        userId,
                        contactInfoRequest.getEmail()
                )
        );
    }

}
