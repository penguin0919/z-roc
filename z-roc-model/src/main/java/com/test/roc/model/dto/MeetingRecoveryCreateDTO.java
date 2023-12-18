package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 编辑回收流水DTO
 *
 * @author z-Roc
 * @date 2023-12-04 14:51
 **/
@Data
public class MeetingRecoveryCreateDTO {
    @ApiModelProperty(value = "主键")
    private String id;
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
