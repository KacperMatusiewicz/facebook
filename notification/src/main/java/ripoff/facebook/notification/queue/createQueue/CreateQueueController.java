package ripoff.facebook.notification.queue.createQueue;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/notification/queue")
@AllArgsConstructor
public class CreateQueueController {

    CreateQueueService service;

    @PostMapping("{userId}")
    public void createQueue(@PathVariable Long userId) {
        service.createQueue(userId);
    }
}
