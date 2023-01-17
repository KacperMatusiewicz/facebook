package ripoff.facebook.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGenerationService {

    private final RestTemplate restTemplate;

    public void registerUsers(int amount){
        ResponseEntity<UserDataGeneratorApiResponse> response = restTemplate.getForEntity(
                "https://randomuser.me/api/?inc=name,last,email&results="+amount, UserDataGeneratorApiResponse.class
        );
        //System.out.println(response.getBody().getResults());
        List<User> users = response.getBody().getResults();
        //users.forEach(user -> System.out.println(user.toString()));

        users.stream()
                .map( user -> {
                    user.setPassword("Qwerty123");
                    return user;
                })
                .map(user ->
                        new RegisterUserRequest(
                                user.getName().getFirst(),
                                user.getName().getLast(),
                                user.getEmail(),
                                user.getPassword()
                        )
                )
                .forEach(user -> {
                    System.out.println("User registration sent: "+ user.getName() +" "+ user.getLastName() +" "+ user.getEmail() + " " + user.getPassword());
                    restTemplate.postForLocation("http://localhost:8080/api/v1/user", user);
                    System.out.println("Request completed");
                });
        //http://localhost:8080/api/v1/user/activate/352
    }

    public void activateUsers(Long startId, Long endId) {
        for (long i = startId; i <= endId; i++) {

            try{
                System.out.println("Activation request sent");
                restTemplate.getForObject("http://localhost:8080/api/v1/user/activate/"+i, String.class);
            } catch (Exception e){
                System.out.println("Activation request failed for: "+ i);
            }

            System.out.println("Activation request completed");
        }
    }
}
