package org.example.practice.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.example.practice.entity.Music;
import org.example.practice.exception.TestException;
import org.example.practice.result.HttpResult;
import org.example.practice.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "音乐上传")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MusicService musicService;


    @Operation(summary = "添加歌曲")
    @PostMapping("/addMusic")
    public HttpResult<String> addMusic(@RequestBody Music music) {
        String s = stringRedisTemplate.opsForValue().get(music.getPosterSign());
        music.setPosterSign(s);
        musicService.add(music);
        return HttpResult.success("文件添加成功");
    }

    @Operation(summary = "歌曲封面上传")
    @PostMapping("/imgUpload")
    @SneakyThrows
    public HttpResult<String> uploadMusic(@RequestPart("file") MultipartFile file) {
        //1.先上传
        String type = file.getContentType();
        if(!type.contains("image")) {
            throw new TestException("图片格式不正确");
        }
        // 2.再验证图片是否存在
        // 3.图片不存在
        //3.1. base64转换
        String encode = Base64.encode(file.getInputStream());
        String ncode = SmUtil.sm3(encode);
        Boolean hasKey = stringRedisTemplate.hasKey(ncode);
//        System.err.println(hasKey);
        if(hasKey) {
            throw new TestException("图片已经存在，上传失败");
        }
//        Music music = new Music();
//        music.setTitle("光年之外");
//        music.setDuration(3);
//        music.setAuthor("邓紫棋");
//        music.setDescription("科幻电影主题曲");
//        music.setLyrics("也许未来遥远在光年之外");
//        music.setPoster(encode);
//        music.setPosterSign(ncode);
        //3.2. 存储到数据库

        stringRedisTemplate.opsForValue().set(ncode,encode);

        return HttpResult.success(ncode);
    }

    @GetMapping("/findMusic")
    @Operation(summary = "使用关键字查询")
    public HttpResult<List<Music>> findMusic(String keyword){
        List<Music>  list = musicService.findByKeyword(keyword);
        return HttpResult.success(list);
    }

}
