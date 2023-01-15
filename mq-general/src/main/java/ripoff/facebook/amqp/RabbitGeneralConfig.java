package ripoff.facebook.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitGeneralConfig {

    @Value("${rabbit-queue-definition.host}")
    private String host;
    @Value("${rabbit-queue-definition.port}")
    private int port;
    @Bean
    public ConnectionFactory generalBusConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean("general-template")
    public RabbitTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(generalBusConnectionFactory());
        rabbitTemplate.setMessageConverter(generalJacksonConverter());
        return rabbitTemplate;
    }

    @Bean("general-converter")
    public MessageConverter generalJacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("general-feed-queue")
    public Queue generalFeedQueue() {
        return new Queue("general-feed-queue", true);
    }

    @Bean("general-notification-queue")
    public Queue generalNotificationQueue() {
        return new Queue("general-notification-queue", true);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory generalContainerFactory(ConnectionFactory generalBusConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(generalBusConnectionFactory);
        factory.setMessageConverter(generalJacksonConverter());
        return factory;
    }
}
