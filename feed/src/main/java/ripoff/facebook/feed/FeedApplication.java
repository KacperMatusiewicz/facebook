package ripoff.facebook.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(
        scanBasePackages = {
                "ripoff.facebook.feed",
                "ripoff.facebook.amqp"
        }
)
@EnableEurekaClient
@EnableRedisRepositories
public class FeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);
    }
}
