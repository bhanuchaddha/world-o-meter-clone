package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import com.bhanuchaddha.worldometerclone.datastreaming.model.Event;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MessagingConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("events");
    }

    @Bean
    public RedisTemplate<String, Event> redisTemplate() {
        final RedisTemplate<String, Event> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        Jackson2JsonRedisSerializer<Event> serializer = new Jackson2JsonRedisSerializer<>(Event.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // Solve the problem that jackson2 cannot deserialize LocalDateTime
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        serializer.setObjectMapper(om);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        // serialize key Time
        StringRedisSerializer srs = new StringRedisSerializer();
        template.setKeySerializer(srs);
        template.setHashKeySerializer(srs);
        template.afterPropertiesSet();
        return template;
    }


}
