package ripoff.facebook.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("initial/self")
    public List<NotificationDTO> getAllOwnNotifications(@RequestHeader("user-id") Long userId) {
        return service.getAllUserNotificationsByUserId(userId);
    }

    @GetMapping("subscribe")
    public SseEmitter subscribeToNewNotifications(@RequestHeader("user-id") Long userId, @CookieValue("JSESSIONID") String sessionId) {
        return service.subscribeToNewNotification(userId, sessionId);
    }

    @DeleteMapping("unsubscribe")
    public void unsubscribeFromNewNotification(@RequestHeader("user-id") Long userId, @CookieValue("JSESSIONID") String sessionId){
        service.unsubscribeToNewNotifications(userId, sessionId);
    }

    @DeleteMapping("delete")
    public void deleteNotification(@RequestHeader("user-id") Long userId, @RequestBody NotificationDTO notificationDTO) {
        service.deleteNotification(userId, notificationDTO);
    }
}
