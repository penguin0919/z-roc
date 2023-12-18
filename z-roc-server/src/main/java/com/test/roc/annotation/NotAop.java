package com.test.roc.annotation;

import java.lang.annotation.*;

/***
 * aop
 *
 * @author z-Roc
 * @date 2023/12/18
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotAop {
    String value() default "";
}
