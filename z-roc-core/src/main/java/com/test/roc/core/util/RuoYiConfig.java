package com.test.roc.core.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoYiConfig
{
    /**
     * jwt 秘钥
     */
    private String jwtSecret;
    /**
     * token 过去时间
     */
    private long jwtMinutes;
    /**
     * AES 秘钥
     */
    private String aesSecret;
    /**
     * 文件上传目录
     */
    private String filePath;
    /**
     * 文件服务器ip
     */
    private String fileHost;

    public String getFileHost() {
        return fileHost;
    }

    public void setFileHost(String fileHost) {
        this.fileHost = fileHost;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAesSecret() {
        return aesSecret;
    }

    public void setAesSecret(String aesSecret) {
        this.aesSecret = aesSecret;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public long getJwtMinutes() {
        return jwtMinutes;
    }

    public void setJwtMinutes(long jwtMinutes) {
        this.jwtMinutes = jwtMinutes;
    }

}
