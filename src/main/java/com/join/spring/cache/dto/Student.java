package com.join.spring.cache.dto;

import java.io.Serializable;

/**
 * @author chi  2018-06-05 17:52
 **/
public class Student implements Serializable{

    private String name;

    private Integer age;

    private Long expireTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student(String name, Integer age,Long expireTime) {
        this.name = name;
        this.age = age;
        this.expireTime = expireTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
