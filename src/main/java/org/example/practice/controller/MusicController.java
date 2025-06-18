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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "音乐上传")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MusicService musicService;

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
        Music music = new Music();
        music.setTitle("2");
        music.setAuthor("2");
        music.setDuration(1);
        music.setPoster(encode);
        music.setPosterSign(ncode);
        musicService.add(music);
        stringRedisTemplate.opsForValue().set(ncode, music.getTitle());
        //3.2. 存储到数据库
        //4. 图片存在
        return HttpResult.success("图片上传成功");
    }
}
