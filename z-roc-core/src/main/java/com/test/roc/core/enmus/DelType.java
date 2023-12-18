package com.test.roc.core.enmus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 删除标识
 *
 * @author z-Roc
 * @date 2023-12-07 09:19
 **/
@Getter
public enum DelType {
    /**
     * 枚举值
     */
    YES(1, "已删除"),
    NO(0, "未删除");

    @ApiModelProperty(value = "标识")
    private final Integer flag;
    @ApiModelProperty(value = "描述")
    private final String remark;

    DelType(Integer flag, String remark) {
        this.flag = flag;
        this.remark = remark;
    }
}
