package com.test.roc.core.util;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* 公共方法类
*
* @description
* @author shangbin
* @date 2021/5/21 13:31
* @param
* @return
* @version v1.0.0
*/
@Slf4j
public class CommonUtil {


    public static HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取原请求头
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        if(enumeration!=null){
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
        }
        return map;
    }


    /**
    * 验证邮箱格式是否正确，格式如下：登录名@主机名.域名
    *
    * @description
    * @author shangbin
    * @date 2021/5/24 19:24
    * @param email
    * @return boolean
    * @version v1.0.0
    */
    public static boolean isEmail(String email) {
        String regex = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
    * 验证ip格式是否正确
    * 
    * @description 
    * @author shangbin
    * @date 2021/7/16 19:28
    * @return boolean 
    * @version v1.4.1
    */
    public static boolean isIp(String ip) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    /**
     * 验证是不是非负整数（正整数   +   0）
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
        String regex = "^\\d+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(number);
        return m.matches();
    }


    /**
     *校验字符串是否匹配正则表达式集合中的至少一项
     * @param url 字符串
     * @param regex 正则表达式集合
     * @return
     */
    public static boolean isWhiteUrl(String url, List<String> regex){
        boolean flag = false;
        for (String rg : regex){
            PathMatcher matcher = new AntPathMatcher();
            flag = matcher.match(rg, url);
            if(flag){
                log.info("匹配白名单地址:{}",rg);
                break;
            }
        }
        return flag;
    }

    public static void isBlank(Object... array){
        for(int i=0;i<array.length;i++){
            if(array[i] instanceof String){
                String str = (String) array[i];
                if(str == null || str.trim().length() == 0){
                    throw MyException.fail(ErrorCode.MYB_111111.getCode(),"必填项为空");
                }
            }else if(array[i] instanceof Integer){
                Integer integer = (Integer) array[i];
                if(integer== null || integer == 0){
                    throw MyException.fail(ErrorCode.MYB_111111.getCode(),"必填项为空");
                }
            }
        }
    }

    /**
     * 通过url获取文件名称
     * @param imgUrl
     * @return
     */
    public static String getImgName(String imgUrl) {
        if (imgUrl == null) {
            return null;
        }
        String[] strs = imgUrl.split("/");
        return strs[strs.length - 1];
    }

    /**
     * 文件字节单位转换
     * @param fileS
     * @return
     */
    public static String formetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


}
