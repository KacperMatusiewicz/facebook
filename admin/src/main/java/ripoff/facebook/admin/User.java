package ripoff.facebook.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Name name;
    private String email;

    private String password;

    @Override
    public String toString() {
        return "User{" +
                "name=" + name.getFirst() +
                ", lastName=" + name.getLast() +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
