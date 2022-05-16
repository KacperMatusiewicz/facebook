package ripoff.facebook.clients.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String name;
    private String lastName;
}
