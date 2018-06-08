package com.join.spring.cache.config;

import com.join.spring.cache.dto.QRCodeType;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.BasicOperation;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomCacheResolver implements CacheResolver {

    @Resource
    private RedisCacheManager cacheManager;

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {

        Object target = context.getTarget();
        Class<?> clazz = target.getClass();

        BasicOperation operation = context.getOperation();

        List<Cache> caches = new ArrayList<Cache>();
        try {
            for (String cacheName : context.getOperation().getCacheNames()) {
                if("QRcode".equals(cacheName)){
                    Object[] args = context.getArgs();
                    for (Object arg : args) {
                        if(QRCodeType.class == arg.getClass()){
                            caches.add(cacheManager.getCache("QRcode:"+arg.toString()));
                        }
                    }
                }else {
                    caches.add(cacheManager.getCache(cacheName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return caches;
    }

}
