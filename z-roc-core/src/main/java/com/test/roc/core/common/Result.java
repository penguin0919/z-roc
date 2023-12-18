package com.test.roc.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result
 *
 * @author z-Roc
 * @version 1.0
 * @date 2023-10-19 11:52
 */
@SuppressWarnings("unused")
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    @ApiModelProperty(value = "错误码")
    private String code = ErrorCode.MYB_000000.getCode();

    @ApiModelProperty(value = "错误描述")
    private String msg = ErrorCode.MYB_000000.getMsg();

    @ApiModelProperty(value = "数据列表")
    private T data;

    public Result(T data) {
        this.data = data;
    }

    private Result(String code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * @param code 错误码
     * @param msg  错误描述信息
     */
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result<Object> OK = new Result<>(ErrorCode.MYB_000000.getCode(), ErrorCode.MYB_000000.getMsg());

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ErrorCode.MYB_000000.getCode(), ErrorCode.MYB_000000.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> fail(String msg) {
        return fail(ErrorCode.MYB_111111.getCode(), msg);
    }

    public static <T> Result<T> success() {
        return new Result<>("000000", "OK");
    }


    public static <T> Result<T> success(String code, T data, String msg) {
        return new Result<>(code, data, msg);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> error(ErrorCode errors) {
        return new Result<>(errors.getCode(), errors.getMsg());
    }

    public static <T> Result<T> error(String code, T data, String msg) {
        return new Result<>(code, data, msg);
    }

    public boolean checkSuccess() {
        return "000000".equals(this.code);
    }

}