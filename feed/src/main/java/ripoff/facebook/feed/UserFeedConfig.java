package ripoff.facebook.feed;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFeedConfig {

    @Bean
    ConnectionFactory userConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5673);
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
