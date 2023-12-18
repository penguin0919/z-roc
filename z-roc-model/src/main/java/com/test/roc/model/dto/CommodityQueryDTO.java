package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 回收商品查询dto
 *
 * @author z-Roc
 * @date 2023-12-04 14:38
 **/
@Data
public class CommodityQueryDTO{
    @ApiModelProperty(value = "回收商品名称")
    private String name;
}
