package ripoff.facebook.notification.preference;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ripoff.facebook.notification.preference.dto.CreatePreferenceRequest;
import ripoff.facebook.notification.preference.repository.*;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class NotificationPreferenceService {

    UserExternalNotificationPreferencesRepository externalNotificationPreferencesRepository;
    UserInternalNotificationPreferencesRepository internalNotificationPreferencesRepository;


    public void createChannelPreference(CreatePreferenceRequest request) {
        externalNotificationPreferencesRepository.save(ExternalNotificationPreference.builder()
                .userId(request.getUserId())
                .preferences(Arrays.asList(
                        ExternalNotificationPreferenceEntry.builder()
                                .notificationMethod(request.getPreferences().get(0).getNotificationMethod())
                                .destination(request.getPreferences().get(0).getDestination())
                                .build()
                ))
                .build());
    }
    public void createApplicationPreference(CreatePreferenceRequest request) {
        internalNotificationPreferencesRepository.save(InternalNotificationPreference.builder()
                .userId(request.getUserId())
                .preferences(Arrays.asList(
                        InternalNotificationPreferenceEntry.builder()
                                .notificationMethod(request.getPreferences().get(0).getNotificationMethod())
                                .destination(request.getPreferences().get(0).getDestination())
                                .build()
                ))
                .build());
    }
    public List<? extends NotificationPreferenceEntry> getUserInternalNotificationPreference(Long id) {
        return internalNotificationPreferencesRepository.getById(id).getPreferences();
    }
    public List<? extends NotificationPreferenceEntry> getUserExternalNotificationPreference(Long id) {
        return externalNotificationPreferencesRepository.getById(id).getPreferences();
    }
}
