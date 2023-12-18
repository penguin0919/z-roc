package com.test.roc.aop;

import com.alibaba.fastjson.JSONObject;
import com.test.roc.annotation.NotAop;
import com.test.roc.config.WhiteConfig;
import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import com.test.roc.core.util.IpUtils;
import com.test.roc.core.util.JwtUtil;
import com.test.roc.core.util.SystemConstants;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 统一打印入参和出参日志格式
 *
 * @author z-Roc
 * @date 2023/12/18
 */
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection", "unused"})
@Component
@Slf4j
@Aspect
public class LoggerAop {
    @Autowired
    private WhiteConfig gatewaySetting;

    private Map<String, String> whiteUrlMap;

    @Autowired
    @Order(20)
    private void whiteUrlListToMap() {
        whiteUrlMap = gatewaySetting.getWhiteUrls().stream().collect(Collectors.toMap(k -> k, v -> v));
    }

    @Pointcut("execution(public * com.test.*.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 校验token并统计接口耗时
     *
     * @param pjp 参数
     */
    @Around(value = "webLog()")
    public Object timeCost(ProceedingJoinPoint pjp) throws Throwable {
        log.info("==========start==========");
        long startTime = System.currentTimeMillis();
        // 获取请求的request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        // 校验token
        /*checkToken(request);*/
        String url = request.getServletPath();
        String params = "";
        String ipAddr = IpUtils.getIpaddr(request);
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        NotAop notAop = methodSignature.getMethod().getAnnotation(NotAop.class);
        if (notAop == null) {
            params = getReqParameter(pjp);
        }
        log.info("@clientIp:{},requestUrl:{},params:{}", ipAddr, url, params);
        Object proceed = pjp.proceed();
        long endTime = System.currentTimeMillis();
        log.info("@接口执行耗时：{} ms", endTime - startTime);
        log.info("@response:{}", proceed == null ? null : proceed.toString());
        log.info("===========end===========");
        return proceed;
    }

    /***
     * 获取请求参数
     * @param joinPoint 参数
     * @return java.lang.String
     * @author z-Roc
     * @date 2023/12/18
     **/
    private String getReqParameter(JoinPoint joinPoint) {
        // 下面两个数组中，参数值和参数名的个数和位置是一一对应的。
        // 参数值
        Object[] args = joinPoint.getArgs();
        // 参数名
        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        JSONObject json = new JSONObject();
        for (int i = 0; i < argNames.length; i++) {
            if ("bindingResult".equalsIgnoreCase(argNames[i])) {
                continue;
            }
            json.put(argNames[i], args[i]);
        }
        return json.toString();
    }

    /**
     * 校验token
     *
     * @param httpRequest 请求头
     */
    private void checkToken(HttpServletRequest httpRequest) {
        String url = httpRequest.getRequestURI();
        log.info("请求地址:{}", url);
        // 白名单不校验，并且不需要token校验
        if (isWhiteUrl(url)) {
            // 白名单路由移除token，避免token过期之后前端依然设置token导致异常
            httpRequest.removeAttribute(SystemConstants.TOKEN);
        } else {
            String token = httpRequest.getHeader(SystemConstants.TOKEN);
            if (StringUtils.isEmpty(token)) {
                throw new MyException(ErrorCode.MYB_111001.getCode(), ErrorCode.MYB_111001.getMsg());
            }
            Claims claims = JwtUtil.parseToken(token);
            if (claims == null) {
                throw new MyException(ErrorCode.MYB_111004.getCode(), ErrorCode.MYB_111004.getMsg());
            }
        }
    }

    /**
     * 如果是白名单中的url，则返回true
     *
     * @param url url
     * @return boolean
     * @author z-Roc
     * @date 2023/12/18
     **/
    private boolean isWhiteUrl(String url) {
        boolean flag = whiteUrlMap.containsKey(url);
        if (flag) {
            log.info("白名单数据:{},无需验签", url);
        }
        return flag;
    }
}