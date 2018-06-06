package com.join.spring.cache.config;

import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chi  2018-06-06 15:14
 **/
public class CustomizedRedisCacheManager extends RedisCacheManager{
    public CustomizedRedisCacheManager(RedisOperations redisOperations) {
        super(redisOperations);
    }

    public CustomizedRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames) {
        super(redisOperations, cacheNames);
    }

    public CustomizedRedisCacheManager(RedisOperations redisOperations, Collection<String> cacheNames, boolean cacheNullValues) {
        super(redisOperations, cacheNames, cacheNullValues);
    }

    private Map<String, CacheTime> cacheTimes = null;
    private long defaultExpiration = 0;

    public void setCacheTimess(Map<String, CacheTime> cacheTimes) {
        this.cacheTimes = (cacheTimes != null ? new ConcurrentHashMap<String, CacheTime>(cacheTimes) : null);
    }
    public void setDefaultExpiration(long defaultExpireTime) {
        this.defaultExpiration = defaultExpireTime;
    }


    protected long computeExpiration(String name) {

        System.out.println("++++++++++++++++++++++++++++=");

        CacheTime cacheTime = null;
        if (cacheTimes != null) {
            cacheTime = cacheTimes.get(name);
        }
        return (cacheTime != null ? cacheTime.getExpirationSecondTime() : defaultExpiration);
    }
}
