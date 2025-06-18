package org.example.practice.service.impl;

import org.example.practice.entity.Music;
import org.example.practice.service.MusicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class IMusicServiceImplTest {

    @Autowired
    private MusicService musicService;

    @Test
    void add() {

//        musicService.add(music);
    }

    @Test
    void remove() {
    }

    @Test
    void loadImageInfo() {
        musicService.loadImageInfo();
    }
}