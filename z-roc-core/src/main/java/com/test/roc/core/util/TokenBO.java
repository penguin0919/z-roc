package com.test.roc.core.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class TokenBO {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 小程序openId
     */
    private String openId;
    /**
     * token过期时间
     */
    private Date expireTime;


}
