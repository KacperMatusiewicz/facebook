package ripoff.facebook.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ripoff.facebook.amqp.NotificationDTO;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImplementation implements NotificationRepository{

    private final RedisTemplate<String, NotificationDTO> redisTemplate;

    @Override
    public void saveNotification(NotificationDTO notificationDTO) {
        redisTemplate.opsForList().leftPush(notificationDTO.getUserId().toString(), notificationDTO);
    }

    @Override
    public List<NotificationDTO> getAllUnreadNotificationsByUserId(Long userId) {
        return redisTemplate.opsForList().range(userId.toString(), 0, -1);
    }

    @Override
    public void deleteByRelationId(NotificationDTO notificationDTO) {
        redisTemplate.opsForList()
                .range(notificationDTO.getUserId().toString(), 0, -1)
                .stream().filter(
                        notification ->
                            notification.getUserId().equals(notificationDTO.getUserId())&&
                            notification.getRelatedId().equals(notificationDTO.getRelatedId())&&
                            notification.getNotificationType().equals(notificationDTO.getNotificationType())&&
                            notification.getContent().equals(notificationDTO.getContent())
                )
                .forEach(
                        notification -> redisTemplate.opsForList().remove(notification.getUserId().toString(), 1, notification)
                );
    }
}
