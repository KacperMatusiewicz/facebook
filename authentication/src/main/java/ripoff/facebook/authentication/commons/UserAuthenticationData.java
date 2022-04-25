package ripoff.facebook.authentication.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAuthenticationData {

    @Id
    private Long id;
    private String login;
    private String password;
}
