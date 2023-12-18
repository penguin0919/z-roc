package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.test.roc.core.common.RecordInfo;
import com.test.roc.core.util.IdUtils;
import com.test.roc.model.dto.MeetingModifyDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**

 * 会议
 * @author z-Roc
 * @date 2023-11-30 17:19
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("会议表")
@TableName(value = "t_meeting")
public class Meeting extends RecordInfo<Meeting, String> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "会议名称")
    private String name;
    @ApiModelProperty(value = "子公司ID")
    private String companyId;
    @ApiModelProperty(value = "会议名称")
    @TableField(exist = false)
    private String companyName;
    @ApiModelProperty(value = "会议地点")
    private String address;
    @ApiModelProperty(value = "会议时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date meetingTime;
    @ApiModelProperty(value = "备注")
    private String remark;

    public Meeting initModifyParams(MeetingModifyDTO modify) {
        this.id = StringUtils.isNotBlank(modify.getId()) ? modify.getId() : IdUtils.fastSimpleUUID();
        this.name = modify.getName();
        this.companyId = modify.getCompanyId();
        this.address = modify.getAddress();
        this.meetingTime = modify.getMeetingTime();
        this.remark = modify.getRemark();
        return this;
    }
}
