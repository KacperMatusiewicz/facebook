package ripoff.facebook.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface UserNotificationQueueClient {

    @PostMapping("api/v1/notification/queue/{userId}")
    void createQueue(@PathVariable("userId") Long userId);

    @DeleteMapping("api/v1/notification/queue/{userId}")
    void deleteQueue(@PathVariable("userId") Long userId);
}
