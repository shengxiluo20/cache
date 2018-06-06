package com.join.spring.cache.config;

/**
 * @author chi  2018-06-06 11:01
 **/

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.Collection;

public class CustomCacheResolver implements CacheResolver {


    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> cacheOperationInvocationContext) {
        return null;
    }
}