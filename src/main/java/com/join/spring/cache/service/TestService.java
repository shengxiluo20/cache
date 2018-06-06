package com.join.spring.cache.service;

import com.join.spring.cache.dto.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author chi  2018-06-05 14:15
 **/
@Component
public class TestService {

//    @Cacheable(value = "test", key="#root.target.getDictTableName() + '_' + #root.target.getFieldName()")
//    @Cacheable(value = "test")


    @Cacheable(value = "test", key="#student.name")
    public String get(Student student){

        System.out.println("缓存数据");
        return "========"+student.getName()+student.getAge();
    }


    public String getDictTableName(){
        return "aaaaaa";
    }

    public String getFieldName(){
        return "bbbbb";
    }
}
