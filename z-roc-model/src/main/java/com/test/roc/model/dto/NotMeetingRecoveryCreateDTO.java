package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 非会议类型回收流水DTO
 *
 * @author z-Roc
 * @date 2023-12-04 14:54
 **/
@Data
public class NotMeetingRecoveryCreateDTO {
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "图片路径")
    private String imgPath;
    @ApiModelProperty(value = "积分总数")
    private Integer integralTotal;
}
