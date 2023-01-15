package ripoff.facebook.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import ripoff.facebook.amqp.NotificationDTO;

@Configuration
public class RedisConfiguration {

    @Value("${redis.url}")
    private String url;
    @Value("${redis.port}")
    private Integer port;

    @Bean
    LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(url);
        configuration.setPort(port);
        configuration.setPassword("eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81");
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<String, NotificationDTO> redisTemplate() {
        RedisTemplate<String, NotificationDTO> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setDefaultSerializer(genericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
}
