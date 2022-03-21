package ripoff.facebook.notification.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.notification.CreatePreferenceRequest;
import ripoff.facebook.notification.Notification;
import ripoff.facebook.notification.NotificationFactory;
import ripoff.facebook.notification.dto.NotificationParameters;
import ripoff.facebook.notification.dto.SendNotificationDTO;
import ripoff.facebook.notification.preferences.NotificationEntry;
import ripoff.facebook.notification.preferences.NotificationMethod;
import ripoff.facebook.notification.preferences.NotificationPreference;
import ripoff.facebook.notification.preferences.UserNotificationPreferencesRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationService {

    private final UserNotificationPreferencesRepository repository;
    private final NotificationFactory factory;

    public void sendNotification(SendNotificationDTO sendNotificationDTO) {

        NotificationPreference preference = repository.
                findById(sendNotificationDTO.getUserId())
                .orElseThrow(() -> new UserPreferencesNotFoundException("user preferences not found"));

        List<Notification> notifications = preference.getPreferences().stream()
                .map((e) -> factory.createNotification(e.getNotificationMethod(), e.getDestination()))
                .collect(Collectors.toList());

        notifications.forEach((notification -> notification.notify(sendNotificationDTO.getParameters())));
    }
// trzeba dopisac fora
    public void createPreference(CreatePreferenceRequest request) {
        repository.save(NotificationPreference.builder()
                        .userId(request.getUserId())
                        .preferences(Arrays.asList(
                                NotificationEntry.builder()
                                        .notificationMethod(request.getPreferences().get(0).getNotificationMethod())
                                        .destination(request.getPreferences().get(0).getDestination())
                                        .build()
                        ))
                .build());
    }
}
