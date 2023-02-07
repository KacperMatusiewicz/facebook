package ripoff.facebook.user.commons;

import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
public class UserEmailView {
    @Id
    private String email;
}
