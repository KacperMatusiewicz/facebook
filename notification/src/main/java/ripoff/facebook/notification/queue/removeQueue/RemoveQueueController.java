package ripoff.facebook.notification.queue.removeQueue;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification/queue")
@AllArgsConstructor
public class RemoveQueueController {

    RemoveQueueService service;

    @DeleteMapping("{userId}")
    public void deleteQueue(@PathVariable Long userId){
        service.deleteQueue(userId);
    }

}
