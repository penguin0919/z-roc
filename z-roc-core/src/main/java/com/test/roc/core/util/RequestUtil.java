package com.test.roc.core.util;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class RequestUtil {
    /**
     * 获取当前用户id
     * @return
     */
    public static Integer getUserId(){
        Claims claims = parseToken();
        Object userId = claims.get(SystemConstants.USER_ID);
        if(userId == null){
            throw MyException.fail(ErrorCode.MYB_111004.getCode(),ErrorCode.MYB_111004.getMsg());
        }
        return (Integer)userId;
    }

    /**
     * 获取当前用户id
     * @return
     */
    public static String getUserName(){
        Claims claims = parseToken();
        Object userName = claims.get(SystemConstants.USER_NAME);
        if(userName == null){
            throw MyException.fail(ErrorCode.MYB_111004.getCode(),ErrorCode.MYB_111004.getMsg());
        }
        return (String)userName;
    }

    /**
     * 解析token
     * @return
     */
    private static Claims parseToken(){
        HttpServletRequest httpServletRequest = CommonUtil.getHttpServletRequest();
        String token = httpServletRequest.getHeader(SystemConstants.TOKEN);
        if(StringUtils.isEmpty(token)){
            throw new MyException(ErrorCode.MYB_111001.getCode(),"token为空");
        }
        Claims claims = JwtUtil.parseToken(token);
        return claims;
    }


}
