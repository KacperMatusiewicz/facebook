package ripoff.facebook.notification.preferences;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.notification.preferences.dto.CreatePreferenceRequest;

@RestController
@RequestMapping("api/v1/notification/preference")
@AllArgsConstructor
public class NotificationPreferenceController {

    NotificationPreferenceService service;

    @PostMapping("/preference")
    public void createPreference(@RequestBody CreatePreferenceRequest request) {
        service.createPreference(request);
    }
}
