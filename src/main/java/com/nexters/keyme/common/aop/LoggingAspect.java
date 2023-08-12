package com.nexters.keyme.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(public * com.nexters.keyme..*(..)) ")
    private void components() { }

    @Pointcut("execution(public * com.nexters.keyme..*Controller.*(..))")
    private void controllers() { }

    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
//        MDC.put("", "");
        Object result = joinPoint.proceed();
        return result;
    }







}
