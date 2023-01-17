package ripoff.facebook.notification;

import com.google.common.collect.HashMultimap;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ripoff.facebook.amqp.NotificationDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class NotificationService {

    private final HashMultimap<Long, NotificationEmitter> sseEmitters;
    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.sseEmitters = HashMultimap.create();
        this.repository = repository;
    }

    public SseEmitter subscribeToNewNotification(Long userId, String sessionId) {

        Optional<NotificationEmitter> notificationEmitter = sseEmitters.get(userId)
                .stream()
                .filter(
                        emitter -> emitter.getSessionId().equals(sessionId)
                )
                .findAny();

        if(notificationEmitter.isEmpty()) {
            NotificationEmitter emitter = new NotificationEmitter(
                    sessionId,
                    new SseEmitter(-1L)
            );
            sseEmitters.put(
                    userId,
                    emitter
            );
            return emitter.getSseEmitter();
        } else {
            return  notificationEmitter.get().getSseEmitter();
        }
    }

    public void unsubscribeToNewNotifications(Long userId, String sessionId) {
        sseEmitters.get(userId)
                .removeIf(
                        notificationEmitter -> notificationEmitter.getSessionId().equals(sessionId)
                );
    }

    public void sendNotification(NotificationDTO notificationDTO) {
        repository.saveNotification(notificationDTO);
        Set<NotificationEmitter> emittersToDelete = new HashSet<>();
        Set<NotificationEmitter> emitterSet = sseEmitters.get(notificationDTO.getUserId());
        emitterSet.forEach(
                notificationEmitter -> {
                    if(!notificationEmitter.sendNotification(notificationDTO)) {
                        emittersToDelete.add(notificationEmitter);
                    }
                }
        );
        emitterSet.removeAll(emittersToDelete);
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
