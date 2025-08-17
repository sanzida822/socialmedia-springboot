package com.example.socialmedia_springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AuthLoggingAspect {
    @Before("execution(* com.example.socialmedia_springboot.controller.AuthenticationController.login(..))")
    public void logBeforeLogin(JoinPoint joinPoint) {
        log.info("Login attempt in method: {}", joinPoint.getSignature().getName());
    }
}
