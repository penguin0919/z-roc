package com.test.roc.core.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class MyException extends RuntimeException {
    private String code;
    private String msg;
    @ApiModelProperty(value = "错误提示数据")
    private final transient Object data;

    public static MyException fail(String code, String msg) {
        return new MyException(code, msg, null);
    }

    public MyException(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    /**
     * 异常-> 根据枚举数据返回错误异常
     *
     * @param errors 异常枚举
     * @author S1cKle
     */
    public MyException(ErrorCode errors) {
        super(errors.getMsg());
        this.data = null;
        if (Objects.isNull(errors.getCode()) || errors.getMsg().isEmpty()) {
            this.code = ErrorCode.MYB_111111.getCode();
            return;
        }
        this.code = errors.getCode();
    }
}
