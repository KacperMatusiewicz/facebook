package ripoff.facebook.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequest {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
