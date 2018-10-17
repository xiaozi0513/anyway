package com.anyway.admin.core.aop;

import com.alibaba.fastjson.JSON;
import com.anyway.admin.core.annotation.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: wang_hui
 * @date: 2018/10/16 下午8:33
 */
@Slf4j
@Aspect
@Component
public class LogEventAopHandler {

    @Pointcut("@annotation(com.anyway.admin.core.annotation.LogEvent)")
    public void logEventPointcut() {

    }

    @Around("logEventPointcut()")
    public Object doAroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        //获取基础信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String remoteAddr = request.getRemoteAddr();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("uri: {} | ip: {} | method: {}", uri, remoteAddr, method);

        //获取注解
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LogEvent logEvent = methodSignature.getMethod().getAnnotation(LogEvent.class);
        boolean isLogParams = logEvent.params();
        boolean isLogResult = logEvent.result();

        if (isLogParams) {
            String[] paramNames = methodSignature.getParameterNames();
            Class[] paramTypes = methodSignature.getParameterTypes();
            Object[] args = joinPoint.getArgs();
            String parameters = "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paramNames.length; i++) {
                if (paramTypes[i].isAssignableFrom(HttpServletRequest.class) || paramTypes[i].isAssignableFrom(HttpServletResponse.class)) {
                    continue;
                }
                sb.append(paramNames[i] + "->" + JSON.toJSONString(args[i]) + " | ");
            }
            if (sb.length() > 0) {
                parameters = sb.substring(0, sb.length() - 3);
            }
            log.info("parameters: {}", parameters);
        }

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        if (isLogResult) {
            log.info("time: {}ms | result: {}", (end - start), JSON.toJSONString(result));
        } else {
            log.info("time: {}ms", (end - start));
        }

        return result;
    }
}
