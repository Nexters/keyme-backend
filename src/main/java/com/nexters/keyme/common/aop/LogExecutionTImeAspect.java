package com.nexters.keyme.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
@Aspect
public class LogExecutionTImeAspect {

    @Pointcut("execution(public * com.nexters.keyme..*(..)) " +
            "&& !execution(public * com.nexters.keyme.common..*(..))" +
            "&& !execution(public * com.nexters.keyme..*Controller.*(..))")
    private void methodExecution() { }

    @Pointcut("execution(public * com.nexters.keyme..*Controller.*(..))")
    private void requestExecution() { }

    @Around("methodExecution()")
    public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = null;
        String targetClass = joinPoint.getTarget().getClass().toString();

        // 시그니처가 메서드 시그니처인지 확인
        Signature signature = joinPoint.getSignature();
        if (signature instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) signature;
            methodName = methodSignature.getMethod().getName();
        }

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("Method ExcutionTime [{} - {}] - 실행시간 : {}", targetClass, methodName, stopWatch.getTotalTimeMillis());
        return result;
    }

    @Around("requestExecution()")
    public Object request(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        log.info("Request ExcuetionTime [{}] - 실행시간 {}", requestURI, stopWatch.getTotalTimeMillis());
        return result;
    }
}
