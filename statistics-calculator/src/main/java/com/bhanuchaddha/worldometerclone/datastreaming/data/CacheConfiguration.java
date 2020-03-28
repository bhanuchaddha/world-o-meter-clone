package com.bhanuchaddha.worldometerclone.datastreaming.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class CacheConfiguration {

    @Bean
    public RedisTemplate<String, Integer> redisCacheTemplate() {
        final RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(new JedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
        return template;
    }
}
