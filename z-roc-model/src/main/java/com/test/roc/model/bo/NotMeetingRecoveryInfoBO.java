package com.test.roc.model.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 非会议类型查询
 *
 * @author z-Roc
 * @date 2023-12-07 10:47
 **/
@Data
public class NotMeetingRecoveryInfoBO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "图片路径")
    private String imgPath;
    @ApiModelProperty(value = "总积分")
    private Long integralTotal;
    @ApiModelProperty(value = "创建人ID")
    private String createId;
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
