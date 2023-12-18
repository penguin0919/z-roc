package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改回收明细
 *
 * @author z-Roc
 * @date 2023-12-04 15:12
 **/
@Data
public class RecoveryModifyDTO {
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
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "图片路径")
    private String imgPath;

}
