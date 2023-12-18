package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**

 * 子公司
 * @author z-Roc
 * @date 2023-12-01 11:02
 **/
@Data
@TableName(value = "t_company")
@ApiModel("公司表")
public class Company {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "公司名称")
    private String name;
}
