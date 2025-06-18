package org.example.practice.service.impl;

import org.example.practice.service.ICacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ICacheServiceImplTest {


    @Autowired
    private ICacheService cacheService;
    @Test
    void add() {
        cacheService.add("fruit", "apple");
    }

    @Test
    void get() {
        System.out.println(cacheService.get("fruit"));
    }
}