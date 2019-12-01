package com.fola.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DurationAspect {

    @Around("@annotation(com.fola.aop.DurationTrackable)")
    public Object duration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long currentTime = System.currentTimeMillis();
        Object response = proceedingJoinPoint.proceed();
        long duration = System.currentTimeMillis() - currentTime;
        log.info("{}.{} completed in {}ms", proceedingJoinPoint.getTarget().getClass().getName(), proceedingJoinPoint.getSignature().getName(), duration);
        return response;
    }
}
