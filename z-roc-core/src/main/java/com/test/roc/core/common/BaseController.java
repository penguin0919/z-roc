package com.test.roc.core.common;

import com.github.pagehelper.PageHelper;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author z-Roc
 * @date 2023-12-04 10:13
 **/
public class BaseController {
    /**
     * 校验入参
     *
     * @param bindingResult bindingResult
     * @author S1cKle
     */
    public void validationFields(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            org.springframework.validation.FieldError fieldError = bindingResult.getFieldError();
            String errorMsg = ErrorCode.COMMON_000001.getMsg();
            if (Objects.nonNull(fieldError)) {
                errorMsg = fieldError.getDefaultMessage();
            }
            throw new MyException(ErrorCode.COMMON_000001.getCode(), errorMsg);
        }
    }


    /**
     * 初始化分页参数
     *
     * @param obj 查询参数
     * @author z-Roc
     * @date 2023/12/12
     **/
    public void initPageHelper(Object obj) {
        // 默认页码
        int pageNum = 1;
        // 默认每页数量
        int pageSize = 10;
        try {
            List<Field> fieldList = new ArrayList<>();
            Class<?> tempClass = obj.getClass();
            while (tempClass != null) {
                // 当父类为null的时候说明到达了最上层的父类(Object类).
                fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                // 得到父类,然后赋给自己
                tempClass = tempClass.getSuperclass();
            }
            for (Field field : fieldList) {
                String fieldName = field.getName();
                switch (fieldName) {
                    case "pageNum":
                        field.setAccessible(true);
                        pageNum = Integer.parseInt(field.get(obj).toString());
                        break;
                    case "pageSize":
                        field.setAccessible(true);
                        pageSize = Integer.parseInt(field.get(obj).toString());
                        break;
                    default:
                        break;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        PageHelper.startPage(pageNum, pageSize);
    }
}
