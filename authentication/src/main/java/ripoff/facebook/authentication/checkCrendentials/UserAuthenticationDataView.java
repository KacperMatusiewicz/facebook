package ripoff.facebook.authentication.checkCrendentials;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
public class UserAuthenticationDataView {

    @Id
    private Long id;
    private String login;
    private String password;
}
