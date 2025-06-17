package org.example.practice.handler;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.example.practice.exception.TestException;
import org.example.practice.result.HttpResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class TestExceptionHandler {

    @ExceptionHandler(TestException.class)
    public HttpResult<String> handleException(TestException e) {
        log.debug("出现异常：{}", e.getMessage());
        return HttpResult.failed(e.getMessage());
    }
}
