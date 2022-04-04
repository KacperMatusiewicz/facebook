package ripoff.facebook.notification.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ripoff.facebook.notification.service.UserNotificationQueueService;

@RestController
@RequestMapping("api/v1/notification/queue")
@AllArgsConstructor
public class UserNotificationQueueController {

    UserNotificationQueueService service;

    @PostMapping("{userId}")
    public void createQueue(@PathVariable Long userId) {
        service.createQueue(userId);
    }

    @DeleteMapping("{userId}")
    public void deleteQueue(@PathVariable Long userId){
        service.deleteQueue(userId);
    }
}
