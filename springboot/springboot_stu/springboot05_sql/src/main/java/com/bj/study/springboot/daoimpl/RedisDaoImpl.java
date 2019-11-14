package com.bj.study.springboot.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisDaoImpl{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public void set(String key, String value) {
        /*this.stringRedisTemplate.opsForValue().set(key, value);*/
        this.stringRedisTemplate.opsForValue().set(key, value,1, TimeUnit.MINUTES);//一分钟过期
    }
    public String get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }
    public void delete(String key) {
        this.stringRedisTemplate.delete(key);
    }
}
