package ripoff.facebook.notification.queue.rabbitConfig;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserNotificationQueueConfiguration {

    @Value("${user-rabbit-queues.host}")
    private String host;
    @Value("${user-rabbit-queues.port}")
    private int port;
    @Bean
    ConnectionFactory userConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    RabbitTemplate template(SimpleRoutingConnectionFactory simpleRoutingConnectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(simpleRoutingConnectionFactory);
        rabbitTemplate.setMessageConverter(userMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory userContainerFactory(ConnectionFactory userConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(userConnectionFactory);
        factory.setMessageConverter(userMessageConverter());
        return factory;
    }

    @Bean
    MessageConverter userMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
