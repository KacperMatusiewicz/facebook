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

@SpringBootApplication(
        scanBasePackages = {
                "ripoff.facebook.feed",
                "ripoff.facebook.amqp"
        },
        exclude = RabbitAutoConfiguration.class
)
@EnableEurekaClient
public class FeedApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class, args);
    }

    //TODO:
    //  ✔ 1.rabbit container na kolejki userow
    //  ✔ 2.rabbit client do odbierania z kolejki rzeczy
    //  ✔ 3.tworzenie kolejek dla kazdego uzytkownika
    //  ✔ 4.wstawianie postow do odpowiednich kolejek
    //  ✔ 5.pobieranie postow z kolejki przez usera
}
