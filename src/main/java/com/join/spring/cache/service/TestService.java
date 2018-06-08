package com.join.spring.cache.service;

import com.join.spring.cache.dto.QRCodeType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author chi  2018-06-05 14:15
 **/
@Component
public class TestService {

//    @Cacheable(value = "test", key="#root.target.getDictTableName() + '_' + #root.target.getFieldName()")
//    @Cacheable(value = "test")

    @Cacheable(value = "QRcode", key = "#naturalKey+ '_' +#qrCodeType")
    public String get(String naturalKey, QRCodeType qrCodeType) {

        System.out.println("缓存数据");
        return "++" + naturalKey + qrCodeType;
    }
}
