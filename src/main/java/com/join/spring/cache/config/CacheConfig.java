package com.join.spring.cache.config;

import com.join.spring.cache.dto.QRCodeType;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chi  2018-06-05 11:47
 **/
@Configuration
@ComponentScan("com.join")
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {


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
        QRCodeType[] values = QRCodeType.values();
        for (QRCodeType qrCodeType : values) {
            expires.put("QRcode:" + qrCodeType.toString(), (long) QRCodeType.getExpireSeconds(qrCodeType));
        }

        cacheManager.setExpires(expires);

//        Map<String, CacheTime> cacheTimes = new HashMap<>();
//        cacheTimes.put("people", new CacheTime(120, 115));
//        cacheTimes.put("test", new CacheTime(120, 115));
//        cacheManager.setCacheTimess(cacheTimes);
        return cacheManager;
    }

    @Resource
    private CustomCacheResolver customCacheResolver;

    @Bean
    @Override
    public CacheResolver cacheResolver() {
        return customCacheResolver;
    }


    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName()).append(":");
            sb.append(method.getName()).append(":");
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
