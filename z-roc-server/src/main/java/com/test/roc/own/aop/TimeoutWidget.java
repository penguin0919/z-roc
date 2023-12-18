package com.test.roc.own.aop;

import com.test.roc.core.common.ErrorCode;

import java.lang.annotation.*;

/**
 * 超时控制器
 *
 * @author z-Roc
 * @date 2023-12-15 11:00
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeoutWidget {
    /**
     * 允许的超时时间, 0L 不计时
     * 单位,毫秒数
     */
    long millisecond() default 0L;

    /**
     * 超时提示语
     */
    ErrorCode error() default ErrorCode.COMMON_TIME_OUT;
}

