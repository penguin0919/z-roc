package com.test.roc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
public class LoginVO {
    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("token过期时间")
    private Date tokenExpireTime;
}
