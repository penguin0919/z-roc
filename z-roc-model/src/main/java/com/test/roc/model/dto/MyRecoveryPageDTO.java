package com.test.roc.model.dto;

import com.test.roc.core.common.Page;
import com.test.roc.model.entity.Recovery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 我的流水
 *
 * @author z-Roc
 * @date 2023-12-04 15:30
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MyRecoveryPageDTO extends Page<Recovery> {
    @ApiModelProperty(value = "我的总积分")
    private String myIntegralTotal;
}
