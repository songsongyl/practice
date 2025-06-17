package org.example.practice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.practice.entity.User;
import org.example.practice.result.HttpResult;
import org.example.practice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Tag(name = "用户接口api")
public class UserController {

    @Autowired
    private IUserService userService;

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public HttpResult<User> register(@RequestBody User user) {
        userService.register(user);
        return HttpResult.success(user);
    }
}
