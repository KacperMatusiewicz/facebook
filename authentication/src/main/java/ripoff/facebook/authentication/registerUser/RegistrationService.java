package ripoff.facebook.authentication.registerUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ripoff.facebook.authentication.commons.UserAuthenticationData;
import ripoff.facebook.authentication.commons.UserAuthenticationDataRepository;
import ripoff.facebook.clients.authentication.AuthenticationMethodDto;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationDataRepository repository;

    public void registerNewUserAuthenticationMethod(AuthenticationMethodDto authenticationMethodDto) {
        repository.save(
                new UserAuthenticationData(
                    authenticationMethodDto.getId(),
                    authenticationMethodDto.getEmail(),
                    passwordEncoder.encode(authenticationMethodDto.getPassword())
                )
        );
    }
}
