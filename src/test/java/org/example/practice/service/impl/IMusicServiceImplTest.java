package org.example.practice.service.impl;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.example.practice.entity.Music;
import org.example.practice.service.MusicService;
import org.example.practice.util.WordsUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class IMusicServiceImplTest {

    @Autowired
    private MusicService musicService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

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

    @Test
    void findByKeyword(){
        List<Music> keywordlist = musicService.findByKeyword("不是");
        keywordlist.forEach(System.out::println);
    }

    //分词优化版
    @Test
    void musicWordTest2(){
        List<Music> list = musicService.findAll();
        for (Music music : list) {
            String keywords = music.getTitle().concat(music.getLyrics().concat(music.getDescription()));
            List<String> words = WordsUtil.getInstance().word(keywords);
            words.forEach(w->{
                stringRedisTemplate.opsForSet().add(w,music.getId().toString());
            });
        }
    }

    //分词处理并存在redis 倒排索引
    @Test
    void musicWordTest(){
        List<Music> list = musicService.findAll();
        JiebaSegmenter js = new JiebaSegmenter();
        for(Music music : list){
//            System.out.println(music);
            String lyrics = music.getLyrics();
            List<String> words = js.sentenceProcess(lyrics);
            for(String keyword : words){
                if(keyword.matches("[的很啊了你我也，]")) continue;
                System.out.println(keyword);
                stringRedisTemplate.opsForSet().add(keyword, music.getId().toString());

            }
            String title = music.getTitle();
            List<String> words2 = js.sentenceProcess(title);
            for(String keyword : words2){
                if(keyword.matches("[的很啊了你我也，]")) continue;
                System.out.println(keyword);
                stringRedisTemplate.opsForSet().add(keyword, music.getId().toString());

            }
        }

    }
}