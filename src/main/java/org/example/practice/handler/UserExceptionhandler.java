package org.example.practice.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.practice.exception.UserRegisterException;
import org.example.practice.result.HttpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionhandler {
    @ExceptionHandler(UserRegisterException.class)
    public HttpResult<String> handleException(UserRegisterException e) {
        log.debug("出现异常“{}", e.getMessage());
        return HttpResult.failed(e.getMessage());
    }
}
