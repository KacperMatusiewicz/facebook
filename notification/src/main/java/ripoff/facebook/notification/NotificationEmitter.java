package ripoff.facebook.notification;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.io.IOException;

@Getter
@Slf4j
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
                    log.info("Trying to send notification. Fail count: " + failCount);
                    sseEmitter.send(notificationDTO, MediaType.APPLICATION_JSON);
                    sseEmitter.complete();
                    sent = true;
                } catch (IOException | RuntimeException e ) {
                    failCount++;
                    if(failCount > MAX_FAIL_COUNT) {
                        log.warn("Sending notification failed.");
                        return false;
                    }
                }
            }
            log.info("Notification successfully sent.");
            failCount = 0;
            return true;
    }

}
