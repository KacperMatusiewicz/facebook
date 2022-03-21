package ripoff.facebook.user;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;

}
