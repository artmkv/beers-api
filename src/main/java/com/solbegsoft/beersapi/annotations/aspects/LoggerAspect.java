package com.solbegsoft.beersapi.annotations.aspects;


import com.solbegsoft.beersapi.annotations.CustomLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Aspect for annotation {@link CustomLogger}
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {

    /**
     * @see CustomLogger
     */
    @Pointcut("@annotation(com.solbegsoft.beersapi.annotations.CustomLogger)")
    public void logMethod() {
    }

    /**
     * @see Around
     */
    @Around("logMethod()")
    public Object logApplication(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String method = methodSignature.getMethod().getName();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = methodSignature.getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            paramMap.put(paramNames[i], args[i]);
        }
        log.info("#GET: method: \"{}\" with request parameters: {}", method, paramMap);
        try {
            Object proceed = joinPoint.proceed();
            log.info("#GET: method: \"{}\" with result {}", method, proceed);
            return proceed;
        } catch (Throwable e) {
            throw  e;
        }
    }
}