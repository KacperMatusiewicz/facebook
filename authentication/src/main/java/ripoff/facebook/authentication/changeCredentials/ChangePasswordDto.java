package ripoff.facebook.authentication.changeCredentials;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ChangePasswordDto {
    private String oldPassword;
    private String newPassword;
}
