package org.example.practice.service.impl;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.crypto.SmUtil;
import org.example.practice.entity.User;
import org.example.practice.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLOutput;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void addUser() {
        for(int i=1;i<10;i++){
            User user = new User();
            user.setUsername("username"+i);
//            Random random = new Random();
            int p = 1000 + i;
            String newP = SmUtil.sm3(String.valueOf(p));
            user.setPassword(newP);
//            user.setPassword(String.valueOf(random.nextInt(9000)+1000));
            user.setName("name"+i);
            userService.addUser(user);
        }
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUsers() {
        userService.findAll().forEach(e -> {
            System.out.println(e.getPassword().length());
        });
    }

    //布隆过滤器
    @Test
    void testBloomFilter() {
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("a");
        filter.add("b");
        filter.add("c");
        System.out.println(filter.contains("d"));
    }
}