package com.bhanuchaddha.worldometerclone.datastreaming.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticsPublisher {
    private RedisTemplate<String, StatisticsMessage> redisStatisticsTemplate;
    private RedisTemplate<String, Integer> redisCacheTemplate;
    private static final String STATISTIC_TOPIC = "statistics";

    @Autowired
    public StatisticsPublisher(@Qualifier("redisStatisticsTemplate") RedisTemplate<String, StatisticsMessage> redisTemplate,
                               @Qualifier("redisCacheTemplate") RedisTemplate<String, Integer> redisCacheTemplate) {
        this.redisStatisticsTemplate = redisTemplate;
        this.redisCacheTemplate = redisCacheTemplate;
    }

    public void publish(String statisticsKey){
        redisStatisticsTemplate.convertAndSend(STATISTIC_TOPIC,
                StatisticsMessage.builder()
                        .key(statisticsKey)
                        .value(redisCacheTemplate.opsForValue().get(statisticsKey))
                        .build()
                );
    }
}
