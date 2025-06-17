package org.example.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "knife4j测试接口的api")
@RestController
@RequestMapping("/api")
@Slf4j
public class HelloController {

    @Operation(summary = "get")
    @GetMapping("/hello")
    public String sayHello() {
        log.debug("11");
        return "Hello World";
    }

    @Operation(summary = "post")
    @PostMapping("/hello")
    public String sayHelloPost() {
        return "Hello";
    }
}
