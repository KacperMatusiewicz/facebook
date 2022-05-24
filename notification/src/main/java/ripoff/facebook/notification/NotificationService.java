package ripoff.facebook.notification;

import com.google.common.collect.HashMultimap;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.util.List;

@Service
public class NotificationService {

    private final HashMultimap<Long, NotificationEmitter> sseEmitters;
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.sseEmitters = HashMultimap.create();
        this.repository = repository;
    }

    public SseEmitter subscribeToNewNotification(Long userId, String sessionId) {

        NotificationEmitter notificationEmitter = sseEmitters.get(userId)
                .stream()
                .filter(
                        emitter -> emitter.getSessionId().equals(sessionId)
                )
                .findAny()
                .orElse(
                        new NotificationEmitter(
                                sessionId,
                                new SseEmitter(-1L)
                        )
                );

        sseEmitters.put(
                userId,
                notificationEmitter
        );

        return  notificationEmitter.getSseEmitter();
    }

    public void unsubscribeToNewNotifications(Long userId, String sessionId) {
        sseEmitters.get(userId)
                .removeIf(
                        notificationEmitter -> notificationEmitter.getSessionId().equals(sessionId)
                );
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        repository.saveNotification(notificationDTO);
        sseEmitters.get(notificationDTO.getUserId()).forEach(
                   notificationEmitter -> {
                       notificationEmitter.sendNotification(notificationDTO);
                   }
       );
    }

    public List<NotificationDTO> getAllUserNotificationsByUserId(Long userId) {
        return repository.getAllUnreadNotificationsByUserId(userId);
    }

    public void deleteNotification(Long userId, NotificationDTO notificationDTO) {

        if(!userId.equals(notificationDTO.getUserId()))
            return;

        repository.deleteByRelationId(notificationDTO);
    }
}
