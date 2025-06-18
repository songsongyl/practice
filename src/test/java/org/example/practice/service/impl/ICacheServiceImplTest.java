package org.example.practice.service.impl;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.example.practice.service.ICacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ICacheServiceImplTest {


    @Autowired
    private ICacheService cacheService;
    @Test
    void add() {
//        cacheService.add("sports", "足球");
//        cacheService.add("sports","篮球");
//        cacheService.add("sp","sp");
            cacheService.add("foot","jiaozi",2);
            cacheService.add("foot","红肠",9);
            cacheService.add("foot","杀猪菜",5);
    }

    @Test
    void get() {
        System.out.println(cacheService.get("fruit"));
    }

    @Test
    void wordTest(){
        JiebaSegmenter js = new JiebaSegmenter();
        List<String> list = js.sentenceProcess("今天去吃大排档，然后听星辰大海");//分词

        list.forEach(s->System.out.println(s));
    }

}