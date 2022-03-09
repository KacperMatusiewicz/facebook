package ripoff.facebook;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private UserStatus userStatus;

}
