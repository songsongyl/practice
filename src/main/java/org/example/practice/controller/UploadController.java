package org.example.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.example.practice.exception.TestException;
import org.example.practice.result.HttpResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

@Tag(name =  "文件上传")
@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Value("${mypath}")
    private String path;

    @Operation(summary = "上传图片")
    @PostMapping("/img")
    @SneakyThrows     //忽略异常
    public HttpResult<String> uploadImg(@RequestPart("file") MultipartFile file) {
//        InputStream inputStream = file.getInputStream(); bio
        System.err.println(file.getContentType());  //MIME
       String type= file.getContentType();
        if(!type.contains("image")) {
            throw new TestException("格式不正确");
        }
        file.transferTo(new File(path + file.getOriginalFilename()));//nio方式
        return HttpResult.success(file.getOriginalFilename());
    }
}
