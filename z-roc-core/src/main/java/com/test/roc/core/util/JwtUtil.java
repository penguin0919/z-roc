package com.test.roc.core.util;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtil {

    private static RuoYiConfig staticRuoYiConfig;

    @Autowired
    public void setRuoYiConfig(RuoYiConfig ruoYiConfig) {
        staticRuoYiConfig = ruoYiConfig;
    }

    /**
     * 加密
     * @param tokenBO
     * @return
     * @throws JWTCreationException
     */
    public static String createToken(TokenBO tokenBO) throws JWTCreationException {
        Map<String, Object> map = new HashMap<>(8);
        map.put(SystemConstants.USER_ID,tokenBO.getUserId());
        map.put(SystemConstants.USER_NAME,tokenBO.getUserName());
        map.put(SystemConstants.OPEN_ID, tokenBO.getOpenId());
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(map).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, staticRuoYiConfig.getJwtSecret());
        if(tokenBO.getExpireTime() != null) {
            jwtBuilder.setExpiration(tokenBO.getExpireTime());
        }
        return jwtBuilder.compact();
    }

    /**
     * 验签 并 解决 token
     * @param encodedToken
     * @return
     */
    public static Claims parseToken(String encodedToken)  {
        Claims claims;
        String jwtSecret = staticRuoYiConfig.getJwtSecret();
        try{
            claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(encodedToken)
                    .getBody();
        }catch (ExpiredJwtException exp){
            log.error("token解析验签失败：{},异常信息：{}", encodedToken, exp.getMessage());
            throw MyException.fail(ErrorCode.MYB_111002.getCode(),ErrorCode.MYB_111002.getMsg());
        }catch (Exception dex){
            throw MyException.fail(ErrorCode.MYB_111004.getCode(),ErrorCode.MYB_111004.getMsg());
        }
        return claims;
    }



}
