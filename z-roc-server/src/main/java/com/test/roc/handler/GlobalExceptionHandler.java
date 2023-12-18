package com.test.roc.handler;

import com.alibaba.fastjson.JSON;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import com.test.roc.core.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * @author 83495
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @SuppressWarnings("unused")
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object allExceptionHandler(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Exception e) {
        // 默认返回系统错误
        Result<String> rs = new Result<>(ErrorCode.MYB_111111.getCode(), ErrorCode.MYB_111111.getMsg());
        if (e instanceof MyException) {
            MyException myException = (MyException) e;

            rs = new Result<>(myException.getCode(), myException.getMsg());
            log.error("@响应参数:{}", JSON.toJSONString(rs));
        } else {
            rs.setData(e.getMessage());
            log.error("@全局异常统一处理:", e);
        }
        return rs;
    }

    /**
     * 处理@Validated参数校验失败异常
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validateExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        BindingResult result = exception.getBindingResult();
        String errors = result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        String url = request.getServletPath();
        Result<String> rs = new Result<>(ErrorCode.MYB_111111.getCode(), errors);
        log.error("@参数校验失败:{},请求接口:{}", JSON.toJSONString(rs), url);
        return rs;
    }
}
