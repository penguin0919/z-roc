package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 非会议类型查询dto
 *
 * @author z-Roc
 * @date 2023-12-07 10:50
 **/
@Data
public class NotMeetingRecoveryQueryDTO {
    @ApiModelProperty(value = "备注参数")
    private String remark;
}
