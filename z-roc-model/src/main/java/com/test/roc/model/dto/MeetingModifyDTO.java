package com.test.roc.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**

 * 编辑会议dto
 * @author z-Roc
 * @date 2023-12-04 09:48
 **/
@Data
public class MeetingModifyDTO {
    @ApiModelProperty(value = "会议ID")
    private String id;
    @ApiModelProperty(value = "会议名称")
    private String name;
    @ApiModelProperty(value = "子公司ID")
    private String companyId;
    @ApiModelProperty(value = "会议地点")
    private String address;
    @ApiModelProperty(value = "会议时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingTime;
    @ApiModelProperty(value = "备注")
    private String remark;
}
