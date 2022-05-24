package ripoff.facebook.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("notification")
public interface UserNotificationQueueClient {

    @DeleteMapping(path="api/v1/notification/")
    void unsubscribeFromNewNotification(@RequestHeader("user-id") Long userId, @CookieValue("JSESSIONID") String sessionId);
}
