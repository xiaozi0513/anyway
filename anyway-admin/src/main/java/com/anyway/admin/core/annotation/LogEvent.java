package com.anyway.admin.core.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author: wang_hui
 * @date: 2018/10/16 下午8:21
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogEvent {

    /**
     * 是否记录请求参数
     */
    boolean params() default true;

    /**
     * 是否记录返回结果
     */
    boolean result() default true;
}
