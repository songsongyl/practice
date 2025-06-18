package org.example.practice.util;

import com.huaban.analysis.jieba.JiebaSegmenter;
import org.example.practice.entity.Music;

import java.util.Iterator;
import java.util.List;

public class WordsUtil {
    private static WordsUtil instance;
    JiebaSegmenter js = new JiebaSegmenter();
    private WordsUtil() {}

    public static synchronized WordsUtil getInstance() {
        if (instance == null) {
            instance = new WordsUtil();
        }
        return instance;
    }

    //分词
    public List<String> word(String sentence) {
        List<String> list = js.sentenceProcess(sentence);
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String word = iterator.next();
            if(word.matches("[的很啊了你我也，]")){
                iterator.remove();
            }
        }
        return list;
    }

}
