package com.test.roc.own.aop;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.Result;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.*;

/**
 * 超时拦截器--合并方式
 *
 * @author z-Roc
 * @date 2023-12-15 15:43
 **/
@Slf4j
public class Timeout {

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface Widget {
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

    @SuppressWarnings("DuplicatedCode")
    @org.aspectj.lang.annotation.Aspect
    @Component
    public static class Aspect{
        /**
         * 超时控制线程池，该池中线程的平均耗时较长
         * 为避免其他线程等待时间过长，使用同步队列
         * coreSize和maxSize根据机器配置按需调整
         */
        public final ThreadPoolExecutor TIMEOUT_POOL = new ThreadPoolExecutor(2, 128, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactoryBuilder().setNameFormat("TimeoutAspect-pool-%d").build());

        @Pointcut("@annotation(com.test.roc.own.aop.Timeout.Widget)")
        public void timeoutPointCut() {
            // do nothing
        }

        @Around("timeoutPointCut()")
        public Object aroundMethodTimeout(ProceedingJoinPoint point) throws Throwable {
            // 获得注解
            Widget widget = getAnnotationLog(point);
            if (Objects.isNull(widget)) {
                return point.proceed();
            }
            long timeSecond = widget.millisecond();
            if (timeSecond == 0L) {
                return point.proceed();
            }
            // 返回值类型,如果没有设置，则使用方法的returnType
            Class<?> returnClazz = widget.getClass();
            Future<?> future = TIMEOUT_POOL.submit(() -> {
                try {
                    return point.proceed();
                } catch (Throwable throwable) {
                    // 根据传入Class生成默认值
                    return getDefaultValue(returnClazz);
                }
            });
            try {
                return future.get(widget.millisecond(), TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                future.cancel(true);
                log.info("接口【{}】响应超过最大设定超时时间【{}】秒", point.getSignature().getName(), widget.millisecond());
                // 根据传入Class生成默认值
                return Result.error(widget.error());
            }
        }

        /**
         * 超过时间后，需要返回的默认值，根据注解传入的Class生成
         *
         * @param clazz 返回值的类型
         * @return 默认值
         */
        private Object getDefaultValue(Class<?> clazz) {
            if (clazz == null || Object.class.equals(clazz)) {
                return null;
            } else if (String.class.equals(clazz)) {
                return "";
            } else if (ArrayList.class.equals(clazz) || List.class.equals(clazz)) {
                return new ArrayList<>(0);
            } else if (HashMap.class.equals(clazz) || Map.class.equals(clazz)) {
                return new HashMap<>(0);
            }
            //使用反射调用无参构造函数返回默认值，如果想自定义返回值，在上面添加即可
            try {
                Constructor<?> constructor = clazz.getConstructor();
                return constructor.newInstance();
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException e) {
                //该类没有无参构造函数时，返回空
                log.error("There is no non-parameter constructor, return null, class：{}", clazz);
                return null;
            } catch (Exception e) {
                log.error("return default value error", e);
                return null;
            }
        }

        private Widget getAnnotationLog(JoinPoint joinPoint) {
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            if (method != null) {
                return method.getAnnotation(Widget.class);
            }
            return null;
        }
    }
}
