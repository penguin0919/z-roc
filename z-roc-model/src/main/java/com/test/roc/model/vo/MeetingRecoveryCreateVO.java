package com.test.roc.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会议类型回收流水VO
 *
 * @author z-Roc
 * @date 2023-12-04 14:50
 **/
@Data
public class MeetingRecoveryCreateVO {
    @ApiModelProperty(value = "回收物品种类ID")
    private String commodityId;
    @ApiModelProperty(value = "所属会议ID")
    private String meetingId;
    @ApiModelProperty(value = "积分总数")
    private Integer integralTotal;
    @ApiModelProperty(value = "回收数量")
    private Integer recoverNum;
    @ApiModelProperty(value = "参与用户数量")
    private Integer userNum;
}
