package com.example.demo.producers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveListOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory factory)
    {
        return new ReactiveStringRedisTemplate(factory);
    }

    @Bean
    public ReactiveListOperations<String,String> listOps(ReactiveStringRedisTemplate redisTemplate)
    {
        return redisTemplate.opsForList();
    }

}
