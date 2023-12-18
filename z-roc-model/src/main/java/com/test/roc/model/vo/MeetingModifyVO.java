package com.test.roc.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**

 * 编辑会议vo
 * @author z-Roc
 * @date 2023-12-04 09:13
 **/
@Data
public class MeetingModifyVO {
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
