package com.join.spring.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chi  2018-06-05 11:47
 **/
@Configuration
public class CacheConfig {


    @Bean
    public RedisTemplate redisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        //各种操作
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);

        // 开启使用缓存名称最为key前缀
        cacheManager.setUsePrefix(true);
        //这里可以设置一个默认的过期时间 单位是秒
        cacheManager.setDefaultExpiration(1000L);

        // 设置缓存的过期时间
        Map<String, Long> expires = new HashMap<>();
        expires.put("people", 1000L);
        expires.put("test", 20L);
        cacheManager.setExpires(expires);
//
//        Map<String, CacheTime> cacheTimes = new HashMap<>();
//        cacheTimes.put("people", new CacheTime(120, 115));
//        cacheTimes.put("test", new CacheTime(120, 115));
//        cacheManager.setCacheTimess(cacheTimes);
//

        return cacheManager;
    }


}
