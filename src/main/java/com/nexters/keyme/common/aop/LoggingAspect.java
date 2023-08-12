package com.nexters.keyme.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(public * com.nexters.keyme..*(..)) ")
    private void methodExecution() { }

    @Pointcut("execution(public * com.nexters.keyme..*Controller.*(..))")
    private void controllerExecution() { }

    @Around("methodExecution()")
    public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
        return checkExecutionTime(joinPoint, null);
    }

    @Around("controllerExecution()")
    public Object request(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();

        return checkExecutionTime(joinPoint, requestURI);
    }

    private Object checkExecutionTime(ProceedingJoinPoint joinPoint, String requestURI) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        String targetClass = joinPoint.getTarget().getClass().toString();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("[{}] - {} 실행시간 : {}", requestURI, targetClass, stopWatch.getTotalTimeMillis());
        return result;
    }
}
