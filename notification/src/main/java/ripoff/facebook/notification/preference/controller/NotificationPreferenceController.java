package ripoff.facebook.notification.preference.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.notification.preference.dto.CreatePreferenceRequest;
import ripoff.facebook.notification.preference.NotificationPreferenceService;

@RestController
@RequestMapping("api/v1/notification/preference")
@AllArgsConstructor
public class NotificationPreferenceController {

    NotificationPreferenceService service;

    @PostMapping("channel")
    public void createChannelPreference(@RequestBody CreatePreferenceRequest request) {
        service.createChannelPreference(request);
    }
    @PostMapping("application")
    public void createApplicationPreference(@RequestBody CreatePreferenceRequest request) {
        service.createApplicationPreference(request);
    }
}
