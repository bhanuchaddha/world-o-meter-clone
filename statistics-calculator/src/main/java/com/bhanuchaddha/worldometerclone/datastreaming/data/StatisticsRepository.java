package com.bhanuchaddha.worldometerclone.datastreaming.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatisticsRepository {

    public StatisticsRepository(@Qualifier("redisCacheTemplate") RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate<String, Integer> redisTemplate;

    public void increment(String key, int count) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().increment(key, count);
        } else {
            redisTemplate.opsForValue().set(key, count);
        }
    }

    public void decrement(String key, int count) {
        if (redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().decrement(key, count);
        } else {
            redisTemplate.opsForValue().set(key, count);
        }
    }
}
