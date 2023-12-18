package com.test.roc.model.vo;

import com.test.roc.core.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 回收商品查询参数vo
 *
 * @author z-Roc
 * @date 2023-12-04 14:37
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityQueryVO extends BasePage {
    @ApiModelProperty(value = "回收商品名称")
    private String name;
}
