package ripoff.facebook.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @GetMapping("initial/self")
    public List<NotificationDTO> getAllOwnNotifications(@RequestHeader("user-id") Long userId) {
        log.info("Received request for all notifications with user id: " + userId);
        return service.getAllUserNotificationsByUserId(userId);
    }

    @GetMapping("subscribe")
    public SseEmitter subscribeToNewNotifications(@RequestHeader("user-id") Long userId, @CookieValue("JSESSIONID") String sessionId) {
        log.info("Received request to subscribe for new notifications with user id: " + userId);
        return service.subscribeToNewNotification(userId, sessionId);
    }

    @DeleteMapping("unsubscribe")
    public void unsubscribeFromNewNotification(@RequestHeader("user-id") Long userId, @CookieValue("JSESSIONID") String sessionId){
        log.info("Received request to unsubscribe from new notifications with user id: " + userId);
        service.unsubscribeToNewNotifications(userId, sessionId);
    }

    @DeleteMapping("delete")
    public void deleteNotification(@RequestHeader("user-id") Long userId, @RequestBody NotificationDTO notificationDTO) {
        log.info(
            "Received request to delete notification with user id: " + userId +
            " and related id: " + notificationDTO.getRelatedId()
        );
        service.deleteNotification(userId, notificationDTO);
    }
}
