package com.test.roc.core.util;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

@Component
@Slf4j
public class AESUtil {

    private static RuoYiConfig ruoYiConfig;

    @Autowired
    public void setRuoYiConfig(RuoYiConfig ruoYiConfig) {
        AESUtil.ruoYiConfig = ruoYiConfig;
    }

    /**
     * 随机生成密钥
     *
     * @return
     */
    public static String getAESRandomKey() {
        SecureRandom random = new SecureRandom();
        long randomKey = random.nextLong();
        return String.valueOf(randomKey);
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(SystemConstants.DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(ruoYiConfig.getAesSecret()));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return byte2Base64(result);
        } catch (Exception ex) {
            log.error("加密失败", ex);
            throw new MyException(ErrorCode.MYB_111111.getCode(),"加密失败");
        }
    }

    /**
     * AES 解密操作
     * @param content 解决文本
     * @return
     */
    public static String decrypt(String content) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(SystemConstants.DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(ruoYiConfig.getAesSecret()));
            //执行操作
            byte[] result = cipher.doFinal(base642Byte(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("解密失败", ex);
            throw new MyException(ErrorCode.MYB_111111.getCode(),"解密失败");
        }
    }

    public static String decrypt(String content,String aesKey) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(SystemConstants.DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(aesKey));
            //执行操作
            byte[] result = cipher.doFinal(base642Byte(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            log.error("解密失败", ex);
            throw new MyException(ErrorCode.MYB_111111.getCode(),"解密失败");
        }
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static Key getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        try {
            KeyGenerator kg = KeyGenerator.getInstance(SystemConstants.KEY_ALGORITHM);
            // 此类提供加密的强随机数生成器 (RNG)，该实现在windows上每次生成的key都相同，但是在部分linux或solaris系统上则不同。
            // SecureRandom random = new SecureRandom(key.getBytes());
            // 指定算法名称，不同的系统上生成的key是相同的。
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            //AES 要求密钥长度为 128
            kg.init(128, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), SystemConstants.KEY_ALGORITHM);
        } catch (Exception ex) {
            log.info("生成加密秘钥异常");
            throw new MyException(ErrorCode.MYB_111111.getCode(),"生成加密秘钥异常");
        }
    }

    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    private static String byte2Base64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    /**
     * Base64编码转字节数组
     * @param base64Key
     * @return
     * @throws Exception
     */
    private static byte[] base642Byte(String base64Key) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }



}
