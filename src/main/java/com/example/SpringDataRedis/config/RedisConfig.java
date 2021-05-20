package com.example.SpringDataRedis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        stringObjectRedisTemplate.setEnableTransactionSupport(true);
        stringObjectRedisTemplate.afterPropertiesSet();
        /* Key as String */
        stringObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringObjectRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        /* Value as Object*/
        stringObjectRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        stringObjectRedisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        return stringObjectRedisTemplate;
    }
}
