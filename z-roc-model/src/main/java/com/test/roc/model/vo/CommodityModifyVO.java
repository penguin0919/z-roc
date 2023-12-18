package com.test.roc.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 回收商品编辑vo
 *
 * @author z-Roc
 * @date 2023-12-04 14:30
 **/
@Data
public class CommodityModifyVO {
    @ApiModelProperty(value = "回收商品名称")
    private String name;
    @ApiModelProperty(value = "回收商品照片路径")
    private String imgPath;
    @ApiModelProperty(value = "所属会议ID")
    private String meetingId;
    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "备注")
    private String remark;
}
