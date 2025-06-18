package org.example.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.example.practice.entity.Music;
import org.example.practice.mapper.IMusicMapper;
import org.example.practice.service.MusicService;
import org.example.practice.util.WordsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Music> findByKeyword(String keyword) {
//        LambdaQueryWrapper<Music> musicL = new LambdaQueryWrapper<>();
//        musicL.like(Music::getTitle, keyword).or().like(Music::getLyrics, keyword);
//        List<Music> list = musicMapper.selectList(musicL);
//        return list;

        List<String> keywords = WordsUtil.getInstance().word(keyword);
        Set<Music> musicset  = new HashSet<>();
        for(String key:keywords){
            Set<String> ids = stringRedisTemplate.opsForSet().members(key);
//            System.err.println(ids);
            if(ids.isEmpty()) continue;
            for(String id:ids){
                Music music = musicMapper.selectById(Integer.parseInt(id));
                musicset.add(music);
            }
        }
//        return musicset.stream().toList();
        return new ArrayList<>(musicset);
    }

    @Override
    public List<Music> findAll() {
       return musicMapper.selectList(null);

    }


}
