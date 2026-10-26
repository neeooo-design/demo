package com.example.demo.aspect;

import com.example.demo.common.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Around("execution(* com.example..demo.controller.*.*(..))")
    public Result around(ProceedingJoinPoint joinPoint){
        System.out.println("在方法执行之前");
        try {
            Result result= (Result) joinPoint.proceed();
            System.out.println(result.toString());
            System.out.println("在方法执行之后");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}
