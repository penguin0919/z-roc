package com.test.roc.model.bo;

import io.swagger.annotations.*;
import lombok.*;

import java.math.*;

@Data
@ApiModel
public class QuotationRecordVO {
    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "项目类型")
    private String projectType;

    @ApiModelProperty(value = "变压器数量", required = true)
    private Integer byqNum;

    @ApiModelProperty(value = "变压器总容量", required = true)
    private Integer byqCapaSum;

    @ApiModelProperty(value = "配电室数量", required = true)
    private Integer pdsNum;

    @ApiModelProperty("每周一次巡检最低价")
    private BigDecimal minPriceWeekInspec;

    @ApiModelProperty("每周一次巡检最高价")
    private BigDecimal maxPriceWeekInspec;

    @ApiModelProperty("每月一次巡检最低价")
    private BigDecimal minPriceMonthInspec;

    @ApiModelProperty("每月一次巡检最高价")
    private BigDecimal maxPriceMonthInspec;
}
