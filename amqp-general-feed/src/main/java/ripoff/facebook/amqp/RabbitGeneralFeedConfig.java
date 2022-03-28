package ripoff.facebook.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitGeneralFeedConfig {

    @Bean
    public ConnectionFactory generalConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean("general-feed-template")
    public RabbitTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(generalConnectionFactory());
        rabbitTemplate.setMessageConverter(generalJacksonConverter());
        return rabbitTemplate;
    }

    @Bean("general-feed-converter")
    public MessageConverter generalJacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("general-feed-queue")
    public Queue generalFeedQueue() {
        return new Queue("general-feed-queue", true);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory generalContainerFactory(ConnectionFactory generalConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(generalConnectionFactory);
        factory.setMessageConverter(generalJacksonConverter());
        return factory;
    }
}
