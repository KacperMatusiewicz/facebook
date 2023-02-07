package ripoff.facebook.user.getUserDetails;

import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Data
public class UserDetailsView {

    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
}
