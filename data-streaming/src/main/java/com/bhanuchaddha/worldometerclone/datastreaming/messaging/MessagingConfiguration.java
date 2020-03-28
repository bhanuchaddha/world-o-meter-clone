package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
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

    @Autowired
    EventMessageSubscriber eventMessageSubscriber;

    @Autowired
    StatisticsMessageSubscriber statisticsMessageSubscriber;

    @Bean
    MessageListenerAdapter messageListener( ) {
        return new MessageListenerAdapter(eventMessageSubscriber);
    }

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        container.addMessageListener(new MessageListenerAdapter(statisticsMessageSubscriber), new ChannelTopic("statistics"));
        container.setRecoveryInterval(2000000);
        container.setTopicSerializer(new StringRedisSerializer());
        return container;
    }


}
