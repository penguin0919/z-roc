package com.test.roc.model.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 王俊生
 * @since 2023/11/7
 */
@ApiModel
@Data
@Accessors(chain = true)
public class LoginBO {

    @ApiModelProperty(value = "openId", required = true)
    @NotEmpty(message = "openId不能为空")
    private String openId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "手机号")
    //@Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号为空或格式不规范")
    private String phoneNumber;
}
