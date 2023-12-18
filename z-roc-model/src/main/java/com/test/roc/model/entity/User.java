package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**

 * 用户
 * @author z-Roc
 * @date 2023-11-30 17:38
 **/
@Data
@ApiModel("用户表")
@TableName(value = "t_user")
public class User {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "角色类型[0：管理员、1：普通用户]")
    private Integer type;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "职务")
    private String dutiesId;
    @ApiModelProperty(value = "公司")
    private String companyId;
    /*@ApiModelProperty(value = "积分总额")
    private Integer integralTotal;*/
}
