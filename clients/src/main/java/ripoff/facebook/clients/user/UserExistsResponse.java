package ripoff.facebook.clients.user;

import lombok.*;

@AllArgsConstructor
@Getter
@Data
@NoArgsConstructor
@Setter
public class UserExistsResponse {
    Boolean userExists;
}
