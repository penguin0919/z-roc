package com.test.roc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author 王俊生
 * @since 2023/11/7
 */
@Configuration
@ConfigurationProperties(prefix = "wx.miniprogram")
@Data
public class WxConfig {
    /**
     * 微信小程序AppId
     */
    private String appid;
    /**
     * 微信小程序AppSecret秘钥
     */
    private String secret;
}
