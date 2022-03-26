package ripoff.facebook.post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "ripoff.facebook.post",
                "ripoff.facebook.amqp"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "ripoff.facebook.clients"
)
public class PostApplication {
    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }
}
