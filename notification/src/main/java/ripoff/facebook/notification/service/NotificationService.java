package ripoff.facebook.notification.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.amqp.NotificationInformation;
import ripoff.facebook.notification.entity.Notification;
import ripoff.facebook.notification.entity.NotificationFactory;
import ripoff.facebook.notification.exception.UserPreferencesNotFoundException;
import ripoff.facebook.notification.preferences.NotificationPreference;
import ripoff.facebook.notification.preferences.UserNotificationPreferencesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationService {

    private final UserNotificationPreferencesRepository repository;
    private final NotificationFactory factory;

    public void sendNotification(NotificationInformation notificationDTO) {

        NotificationPreference preference = repository.
                findById(notificationDTO.getUserId())
                .orElseThrow(() -> new UserPreferencesNotFoundException("user preferences not found"));

        List<Notification> notifications = preference.getPreferences().stream()
                .map((e) -> factory.createNotification(e.getNotificationMethod(), e.getDestination()))
                .collect(Collectors.toList());

        notifications.forEach((notification -> notification.notify(notificationDTO.getNotificationParameters())));
    }
}
