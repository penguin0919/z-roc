package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**

 * 职务
 * @author z-Roc
 * @date 2023-12-01 11:04
 **/
@Data
@ApiModel("职务表")
@TableName(value = "t_duties")
public class Duties {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "职务ID")
    private String id;
    @ApiModelProperty(value = "职务名称")
    private String name;
}
