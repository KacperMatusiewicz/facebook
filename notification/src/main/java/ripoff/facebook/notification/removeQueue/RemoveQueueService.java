package ripoff.facebook.notification.removeQueue;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RemoveQueueService {
    private final RabbitAdmin userRabbitAdmin;

    public RemoveQueueService(@Qualifier("userContainerFactory-admin") RabbitAdmin userRabbitAdmin) {
        this.userRabbitAdmin = userRabbitAdmin;
    }

    public void deleteQueue(Long userId) {
        userRabbitAdmin.deleteQueue(userId.toString());
    }
}
