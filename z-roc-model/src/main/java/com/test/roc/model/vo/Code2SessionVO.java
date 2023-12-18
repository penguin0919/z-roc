package com.test.roc.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class Code2SessionVO {
    @ApiModelProperty("用户唯一标识")
    private String openId;

    @ApiModelProperty("会话密钥")
    private String sessionKey;
}
