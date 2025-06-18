package org.example.practice.service.impl;

import org.example.practice.entity.Music;
import org.example.practice.mapper.IMusicMapper;
import org.example.practice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMusicServiceImpl implements MusicService {

    @Autowired
    private IMusicMapper musicMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void add(Music music) {
        musicMapper.insert(music);
    }

    @Override
    public void remove(Music music) {
        musicMapper.deleteById(music.getId());
    }

    @Override
    public void loadImageInfo() {
        List<Music> music = musicMapper.selectList(null);
        music.forEach(m->{
            stringRedisTemplate.opsForValue().set(m.getPosterSign(),m.getPoster());
        });
    }


}
