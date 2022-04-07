package ripoff.facebook.feed;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryContextWrapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
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

    //TODO:
    //  1. store key-value post user in database
    //  2. create in memory queue to store feed posts for each active user
    //  3.
    //  4.
}
