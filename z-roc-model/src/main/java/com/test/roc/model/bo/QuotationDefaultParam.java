package com.test.roc.model.bo;

import io.swagger.annotations.*;
import lombok.*;

import java.math.*;
import java.util.*;

@Data
@ApiModel
public class QuotationDefaultParam {
    @ApiModelProperty(value = "基准容量")
    public static List<String> Capa_Bmark = new ArrayList<String>() {{
        add("1600");
        add("1250");
        add("800");
    }};
    @ApiModelProperty("巡检基准价")
    public static BigDecimal Inspec_Bmark = new BigDecimal("6");
    @ApiModelProperty("试验基准价（3年一次，每次价格/3）")
    public static BigDecimal Prevent_Bmark = new BigDecimal("12000");
    @ApiModelProperty("地区热度")
    public static BigDecimal K_Hot = new BigDecimal("1");
    @ApiModelProperty("线上监管价格")
    public static BigDecimal OnLine = new BigDecimal("16800");
}
