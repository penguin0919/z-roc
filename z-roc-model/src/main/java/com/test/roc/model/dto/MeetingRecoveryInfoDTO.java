package com.test.roc.model.dto;

import com.test.roc.model.entity.Recovery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 会议回收详情
 *
 * @author z-Roc
 * @date 2023-12-07 09:49
 **/
@Data
public class MeetingRecoveryInfoDTO {
    @ApiModelProperty(value = "会议下的总积分")
    private String totalIntegral;
    @ApiModelProperty(value = "流水列表详情")
    private List<Recovery> recoveryList;
}
