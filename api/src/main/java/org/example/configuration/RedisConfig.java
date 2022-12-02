package org.example.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Data
    static class RedisProperty {
        private String host;
        private int port;
    }

    @ConfigurationProperties(prefix = "spring.redis")
    @Bean("RedisSource")
    public RedisProperty redisSource() {
        return new RedisProperty();
    }

    @Bean(name = "RedisConnectionFac")
    public RedisConnectionFactory userRedisConnectionFactory(@Qualifier("RedisSource") RedisProperty redisProperty) {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(redisProperty.getHost());
        redisConfiguration.setPort(redisProperty.getPort());
        return new LettuceConnectionFactory(redisConfiguration);
    }

    @Bean("RedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("RedisConnectionFac") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new StringRedisSerializer());
        return template;
    }
}
