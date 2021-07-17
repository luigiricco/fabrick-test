package com.fabrick.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before(value = "execution(* com.fabrick..*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint)  {
        log.debug("[START] {}", joinPoint.getSignature());
    }

    @After(value = "execution(* com.fabrick..*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint)  {
        log.debug("[END] {}", joinPoint.getSignature());
    }

}
