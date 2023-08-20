package com.nexters.keyme.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
@Aspect
public class QueryExecutionTimeAspect {

    @Pointcut("execution(public * com.nexters.keyme..*Repository.*(..))")
    private void queryExecution() { }

    @Around("queryExecution()")
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

        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        if (totalTimeMillis > 500) {
            log.warn("Slow Query 발생! [{} - {}] - 실행시간 : {}", targetClass, methodName, totalTimeMillis);
        } else {
            log.debug("Method ExcutionTime [{} - {}] - 실행시간 : {}", targetClass, methodName, stopWatch.getTotalTimeMillis());
        }

        return result;
    }
}
