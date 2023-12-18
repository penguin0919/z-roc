package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.test.roc.core.common.RecordInfo;
import com.test.roc.core.enmus.RecoveryType;
import com.test.roc.core.util.IdUtils;
import com.test.roc.model.dto.MeetingRecoveryCreateDTO;
import com.test.roc.model.dto.NotMeetingRecoveryCreateDTO;
import com.test.roc.model.dto.RecoveryModifyDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回收清单
 *
 * @author z-Roc
 * @date 2023-11-30 17:25
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("回收流水表")
@TableName(value = "t_recovery")
public class Recovery extends RecordInfo<Recovery, String> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "回收清单种类[1.管理端查看、2.用户端查看]")
    private RecoveryType type;
    @ApiModelProperty(value = "回收物品种类ID")
    private String commodityId;
    @ApiModelProperty(value = "回收商品种类名称")
    @TableField(exist = false)
    private String commodityName;
    @ApiModelProperty(value = "所属会议ID")
    private String meetingId;
    @ApiModelProperty(value = "会议名称")
    @TableField(exist = false)
    private String meetingName;
    @ApiModelProperty(value = "积分总数")
    private Integer integralTotal;
    @ApiModelProperty(value = "回收数量")
    private Integer recoverNum;
    @ApiModelProperty(value = "参与用户数量")
    private Integer userNum;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "图片路径")
    private String imgPath;

    /**
     * 新增会议类型回收清单
     *
     * @param meeting 新增参数
     * @return com.test.assistant.model.entity.Recovery
     * @author z-Roc
     * @date 2023/12/4
     **/
    public Recovery initMeetingRecoveryParams(MeetingRecoveryCreateDTO meeting) {
        this.id = IdUtils.fastSimpleUUID();
        this.commodityId = meeting.getCommodityId();
        this.meetingId = meeting.getMeetingId();
        this.integralTotal = meeting.getIntegralTotal();
        this.recoverNum = meeting.getRecoverNum();
        this.userNum = meeting.getUserNum();
        return this;
    }

    /**
     * 新增非会议类型回收清单
     *
     * @param notMeeting 新增参数
     * @return com.test.assistant.model.entity.Recovery
     * @author z-Roc
     * @date 2023/12/4
     **/
    public Recovery initNotMeetingRecoveryParams(NotMeetingRecoveryCreateDTO notMeeting) {
        this.id = IdUtils.fastSimpleUUID();
        this.remark = notMeeting.getRemark();
        this.imgPath = notMeeting.getImgPath();
        this.integralTotal = notMeeting.getIntegralTotal();
        // 给所有非会议类型设置会议ID为not_meeting,以防止空值查询效率降低
        this.meetingId = "not_meeting";
        return this;
    }

    /**
     * 初始化修改参数
     *
     * @param modify 修改参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    public void initUpdateParams(RecoveryModifyDTO modify) {
        this.id = modify.getId();
        this.type = RecoveryType.MANAGE;
        this.commodityId = modify.getCommodityId();
        this.meetingId = modify.getMeetingId();
        this.integralTotal = modify.getIntegralTotal();
        this.recoverNum = modify.getRecoverNum();
        this.userNum = modify.getUserNum();
        this.remark = modify.getRemark();
        this.imgPath = modify.getImgPath();
    }
}
