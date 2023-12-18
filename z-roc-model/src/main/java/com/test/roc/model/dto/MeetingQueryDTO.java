package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会议查询参数
 *
 * @author z-Roc
 * @date 2023-12-04 10:31
 **/
@Data
public class MeetingQueryDTO {
    @ApiModelProperty(value = "会议名称")
    private String name;
    @ApiModelProperty(value = "查询用户ID")
    private String uid;
}
