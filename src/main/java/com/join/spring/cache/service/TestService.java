package com.join.spring.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author chi  2018-06-05 14:15
 **/
@Component
public class TestService {

    @Cacheable(value = "test#120#90", key="#a")
    public String get(String a ,String b){

        System.out.println("未走缓存");
        return "========"+a+b;
    }
}
