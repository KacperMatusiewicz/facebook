package ripoff.facebook.notification;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ripoff.facebook.notification.dto.SendNotificationDTO;
import ripoff.facebook.notification.service.NotificationService;

@RestController
@RequestMapping("api/v1/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public void sendNotification(@RequestBody SendNotificationDTO sendNotificationDTO) {
        service.sendNotification(sendNotificationDTO);
    }

    @PostMapping("/preference")
    public void createPreference(@RequestBody CreatePreferenceRequest request) {
        service.createPreference(request);
    }
}
