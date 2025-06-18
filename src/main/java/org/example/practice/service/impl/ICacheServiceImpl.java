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
    public void add(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
