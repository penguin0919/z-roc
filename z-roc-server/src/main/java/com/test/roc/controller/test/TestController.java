package com.test.roc.controller.test;

import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.Result;
import com.test.roc.own.aop.Timeout;
import com.test.roc.own.aop.TimeoutWidget;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author z-Roc
 * @date 2023-12-15 10:53
 **/
@RestController
@RequestMapping("test")
@Api(tags = "测试")
public class TestController {
    @ApiOperation("测试超时-> 拆分成两个类")
    @TimeoutWidget(millisecond = 1000, error = ErrorCode.TEST_INTERFACE_TIMEOUT)
    @GetMapping("/aop/timeout")
    public Result<String> testAopTimeout() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    @ApiOperation("测试超时-> 两个类合并方式")
    @Timeout.Widget(millisecond = 1000, error = ErrorCode.TEST_INTERFACE_TIMEOUT)
    @GetMapping("/aop/timeout2")
    public Result<String> testAopTimeout2() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }
}
