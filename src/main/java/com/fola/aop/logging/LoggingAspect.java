package com.fola.aop.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final ObjectMapper objectMapper;

    public LoggingAspect(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Pointcut("@annotation(com.fola.aop.Loggable)")
    public void applicationPointcut() {
        // Point cut for top level application package
    }

    @Around("applicationPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.debug("Invoked: {}.{} with args {}", proceedingJoinPoint.getTarget().getClass().getName(),
                proceedingJoinPoint.getSignature().getName(), objectMapper.writeValueAsString(proceedingJoinPoint.getArgs()));
        Object result = proceedingJoinPoint.proceed();
        log.debug("Response: {}.{} =: {}", proceedingJoinPoint.getTarget().getClass().getName(),
                proceedingJoinPoint.getSignature().getName(), objectMapper.writeValueAsString(result));
        return result;
    }

    @AfterThrowing(pointcut = "applicationPointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        log.error("Exception from {}.{}, cause: {}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature(), throwable.getCause() != null ? throwable.getCause() : "NULL");
    }

}
