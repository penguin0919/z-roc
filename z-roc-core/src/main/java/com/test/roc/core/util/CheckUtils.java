package com.test.roc.core.util;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * <p>
 *
 * </p>
 *
 * @author 王俊生
 * @since 2022/11/3
 */
public class CheckUtils {

    public static void checkIntegerNull(Integer param, String msg) {
        if (IntegerUtil.isNull(param)) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void checkStringNull(String param, String msg) {
        if (StringUtils.isEmpty(param)) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void checkCollectionNull(Collection param, String msg) {
        if (CollectionUtils.isEmpty(param)) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void checkObjectNull(Object param, String msg) {
        if (param == null) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void checkObjectNotNull(Object param, String msg) {
        if (param != null) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void isTrue(boolean expression, String msg) {
        if (!expression) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }

    public static void isFalse(boolean expression, String msg) {
        if (expression) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }


    public static void checkStringBlank(String param, String msg) {
        if (StringUtils.isEmpty(param)|| StringUtils.isBlank(param)) {
            throw new MyException(ErrorCode.MYB_111111.getCode(), msg);
        }
    }
}
