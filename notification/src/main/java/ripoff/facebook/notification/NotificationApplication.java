package ripoff.facebook.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import ripoff.facebook.notification.commons.NotificationMethod;
import ripoff.facebook.notification.preference.repository.*;

import java.util.Arrays;

@SpringBootApplication(
        scanBasePackages = {
                "ripoff.facebook.amqp",
                "ripoff.facebook.notification"
        },
        exclude = RabbitAutoConfiguration.class
)
@EnableEurekaClient
public class NotificationApplication {
    @Autowired
    UserInternalNotificationPreferencesRepository internalNotificationPreferencesRepository;
    @Autowired
    UserExternalNotificationPreferencesRepository externalNotificationPreferencesRepository;

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }
    
    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            InternalNotificationPreferenceEntry internalEntry = InternalNotificationPreferenceEntry
                    .builder()
                    .notificationMethod(NotificationMethod.MOBILE_APP)
                    .destination("xd")
                    .build();
            InternalNotificationPreference internalNotificationPreference = InternalNotificationPreference
                    .builder()
                    .userId(1L)
                    .preferences(Arrays.asList(internalEntry))
                    .build();

            ExternalNotificationPreferenceEntry externalEntry = ExternalNotificationPreferenceEntry
                    .builder()
                    .notificationMethod(NotificationMethod.MAIL)
                    .destination("xd@xd.pl")
                    .build();
            ExternalNotificationPreference externalNotificationPreference = ExternalNotificationPreference
                    .builder()
                    .userId(1L)
                    .preferences(Arrays.asList(externalEntry))
                    .build();
            internalNotificationPreferencesRepository.save(internalNotificationPreference);
            externalNotificationPreferencesRepository.save(externalNotificationPreference);
        };

    }
}
