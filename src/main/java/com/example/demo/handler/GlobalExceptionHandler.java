package com.example.demo.handler;

import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result handlerException(RuntimeException e) {
        return Result.error(e.getMessage());
    }
}
