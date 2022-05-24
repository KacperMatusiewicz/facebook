package ripoff.facebook.relation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
        scanBasePackages = {
        "ripoff.facebook.relation",
        "ripoff.facebook.amqp"
        }
)
@EnableEurekaClient
public class RelationApplication {
    public static void main(String[] args) {
        SpringApplication.run(RelationApplication.class, args);
    }
}
