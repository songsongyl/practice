package org.example.practice.service.impl;

import org.example.practice.service.ICacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ICacheServiceImpl implements ICacheService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(String key, String value,double right) {
//        stringRedisTemplate.opsForValue().set(key, value);
//        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForZSet().add(key,value,right);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
