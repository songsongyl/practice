package org.example.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.example.practice.exception.TestException;
import org.example.practice.result.HttpResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "测试返回结果")
@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {
    @Operation(summary = "测试返回结果")
    @GetMapping("/test1")
    public HttpResult<String> test1() {
//        int a = 3;
//        int b = 4;
//        if (a < b) throw new TestException("测试异常");
//        log.debug("11");
        return HttpResult.success("测试get请求成功");
    }
}
