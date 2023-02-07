package ripoff.facebook.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdminApplication {

    UserGenerationService userGenerationService;

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

    @Autowired
    public void setUserGenerationService(UserGenerationService userGenerationService) {
        this.userGenerationService = userGenerationService;
    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
//            userGenerationService.registerUsers(10);
//                userGenerationService.activateUsers(1L, 10L);

        };
    }
}
