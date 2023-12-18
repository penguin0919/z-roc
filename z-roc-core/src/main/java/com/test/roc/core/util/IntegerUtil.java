package com.test.roc.core.util;

/**
 * <p>
 *
 * </p>
 *
 * @author 王俊生
 * @since 2022/10/17
 */
public class IntegerUtil {
    public static boolean isNull(Integer i) {
        if (i == null || i == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Integer i) {
        return !isNull(i);
    }
}
