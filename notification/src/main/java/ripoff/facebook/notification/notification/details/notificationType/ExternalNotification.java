package ripoff.facebook.notification.notification.details.notificationType;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ripoff.facebook.notification.notification.NotificationDTO;
import ripoff.facebook.notification.notification.details.NotificationPreference;
import ripoff.facebook.notification.notification.NotificationTemplate;
import ripoff.facebook.notification.notification.details.NotificationPreferenceEntryCollection;
import ripoff.facebook.notification.preference.NotificationPreferenceService;
import ripoff.facebook.notification.preference.repository.NotificationPreferenceEntry;
import ripoff.facebook.notification.preference.repository.UserExternalNotificationPreferencesRepository;

import java.util.ArrayList;
import java.util.List;

@Setter
@AllArgsConstructor
public class ExternalNotification extends NotificationTemplate {

    private NotificationPreferenceService preferenceService;
    @Override
    public NotificationPreference retrieveNotificationPreferences(Long userId) {
        List<NotificationPreferenceEntry> preferences = new ArrayList<>();
        preferences.addAll(preferenceService.getUserExternalNotificationPreference(userId));
        return () -> new NotificationPreferenceEntryCollection(preferences);
    }

    @Override
    public void log(NotificationDTO information) {
        System.out.println(
                "Sending notification for: "
                + information.getUserId()
                + ", with type: CHANNEL."
        );
    }

}
