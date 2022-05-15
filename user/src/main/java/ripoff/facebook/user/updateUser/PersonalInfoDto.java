package ripoff.facebook.user.updateUser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PersonalInfoDto {

    private final  Long userId;
    private final String name;
    private final String lastName;

}
