package ripoff.facebook.notification;

import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.io.IOException;

@Getter
public class NotificationEmitter {

    private final static int MAX_FAIL_COUNT = 10;
    private final String sessionId;
    private final SseEmitter sseEmitter;
    private byte failCount;

    public NotificationEmitter(String sessionId, SseEmitter sseEmitter) {
        this.sessionId = sessionId;
        this.sseEmitter = sseEmitter;
        this.failCount = 0;
    }

    public boolean sendNotification(NotificationDTO notificationDTO) {
            boolean sent = false;
            while(!sent) {
                try {
                    sseEmitter.send(notificationDTO, MediaType.APPLICATION_JSON);
                    //sseEmitter.complete();
                    sent = true;
                } catch (IOException e) {
                    failCount++;
                    if(failCount > MAX_FAIL_COUNT) {
                        return false;
                    }
                }
            }
            failCount = 0;
            return true;
    }

}
