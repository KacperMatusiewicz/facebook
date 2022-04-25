package ripoff.facebook.clients.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationMethodDto {

    private Long id;
    private String email;
    private String password;
}
