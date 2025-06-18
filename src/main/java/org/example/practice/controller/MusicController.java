package org.example.practice.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SmUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.example.practice.entity.Music;
import org.example.practice.exception.TestException;
import org.example.practice.result.HttpResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "音乐上传")
@RestController
@RequestMapping("/api/music")
public class MusicController {

    @PostMapping("/imgUpload")
    @SneakyThrows
    public HttpResult<String> uploadMusic(@RequestPart("file") MultipartFile file) {
        //1.先上传
        String type = file.getContentType();
        if(!type.contains("image")) {
            throw new TestException("格式不正确");
        }
        // 2.再验证图片是否存在
        // 3.图片不存在
        //3.1. base64转换
        String encode = Base64.encode(file.getInputStream());
        Music music = new Music();
        music.setTitle("2");
        music.setAuthor("2");
        music.setDuration(1);
        music.setPoster(encode);
        String ncode = SmUtil.sm3(encode);
        music.setPosterSign(ncode);
        //3.2. 存储到数据库
        //4. 图片存在
        return HttpResult.success(file.getOriginalFilename());
    }
}
