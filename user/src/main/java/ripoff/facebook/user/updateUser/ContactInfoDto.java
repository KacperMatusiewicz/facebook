package ripoff.facebook.user.updateUser;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ContactInfoDto {
    private final Long userId;
    private final String email;
}
