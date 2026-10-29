package com.example.demo.handler;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotPermissionException;
import com.example.demo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice()
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Result handlerException(RuntimeException e) {
        return Result.error(e.getMessage());
    }
    @ExceptionHandler(NotPermissionException.class)
    public Result handlerNotPermissionException(NotPermissionException e){
        SaHolder.getResponse().setStatus(403);
        return Result.error(e.getMessage());
    }
}
