package ripoff.facebook.feed;

import lombok.AllArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.amqp.rabbit.annotation.MultiRabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactoryContextWrapper;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

@Configuration
public class MultipleRabbitConfig {

    @Bean
    SimpleRoutingConnectionFactory routingConnectionFactory(
            ConnectionFactory generalConnectionFactory,
            ConnectionFactory userConnectionFactory
    ) {
        SimpleRoutingConnectionFactory factory = new SimpleRoutingConnectionFactory();
        factory.setDefaultTargetConnectionFactory(generalConnectionFactory);
        factory.setTargetConnectionFactories(
                Map.of("general", generalConnectionFactory, "user", userConnectionFactory)
        );
        return factory;
    }

    @Bean("userContainerFactory-admin")
    public RabbitAdmin userRabbitAdmin(ConnectionFactory userConnectionFactory){
        return new RabbitAdmin(userConnectionFactory);
    }

    @Bean("generalContainerFactory-admin")
    public RabbitAdmin generalRabbitAdmin(ConnectionFactory generalConnectionFactory){
        return new RabbitAdmin(generalConnectionFactory);
    }

    @Bean
    @Primary
    public RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry() {
        return new RabbitListenerEndpointRegistry();
    }

    @Bean
    public RabbitListenerAnnotationBeanPostProcessor postProcessor(RabbitListenerEndpointRegistry registry) {
        MultiRabbitListenerAnnotationBeanPostProcessor postProcessor = new MultiRabbitListenerAnnotationBeanPostProcessor();
        postProcessor.setEndpointRegistry(registry);
        postProcessor.setContainerFactoryBeanName("defaultContainerFactory");
        return postProcessor;
    }

    @Bean
    public ConnectionFactoryContextWrapper wrapper(SimpleRoutingConnectionFactory simpleRoutingConnectionFactory) {
        return new ConnectionFactoryContextWrapper(simpleRoutingConnectionFactory);
    }

}

