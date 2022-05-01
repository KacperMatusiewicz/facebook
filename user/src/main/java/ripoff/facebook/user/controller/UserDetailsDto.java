package ripoff.facebook.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailsDto {

    private final String name;
    private final String lastName;
    private final String email;

}
