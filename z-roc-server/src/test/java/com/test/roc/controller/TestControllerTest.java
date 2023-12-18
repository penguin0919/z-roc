package com.test.roc.controller;

import com.test.roc.core.util.RuoYiConfig;
import com.test.roc.core.util.JwtUtil;
import com.test.roc.core.util.TokenBO;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestControllerTest {
    @Autowired
    private RuoYiConfig ruoYiConfig;
    @Test
    public void testCreateToken(){
        TokenBO tokenBO = new TokenBO();
        Date date = new Date();
        tokenBO.setExpireTime(DateUtils.addMinutes(date, ruoYiConfig.getJwtMinutes() == 0 ? 1400 : 1400 + (int) ruoYiConfig.getJwtMinutes()));
        tokenBO.setUserId(1);
        tokenBO.setUserName("张三");
        tokenBO.setOpenId("sodgoaohgngIad");
        String token = JwtUtil.createToken(tokenBO);
        System.out.println(token);
    }
}