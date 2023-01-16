package ripoff.facebook.user.updateUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UpdateUserController {

    private final UpdateUserService service;

    @PutMapping("personal")
    public void changePersonalInfo(@RequestHeader("user-id") Long userId, @RequestBody PersonalInfoRequest personalInfoRequest){
        log.info("Received request to change personal info for user id: " + userId);
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
        log.info("Received request to change contact info for user id: " + userId);
        service.changeContactInfo(
                new ContactInfoDto(
                        userId,
                        contactInfoRequest.getEmail()
                )
        );
    }

}
