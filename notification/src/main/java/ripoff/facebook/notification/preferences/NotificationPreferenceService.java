package ripoff.facebook.notification.preferences;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ripoff.facebook.notification.preferences.dto.CreatePreferenceRequest;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class NotificationPreferenceService {

    UserNotificationPreferencesRepository repository;

    //trzeba dopisac fora
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
