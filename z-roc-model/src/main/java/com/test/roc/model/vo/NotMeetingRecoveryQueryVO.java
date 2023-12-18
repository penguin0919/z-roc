package com.test.roc.model.vo;

import com.test.roc.core.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 非会议类型查询VO
 *
 * @author z-Roc
 * @date 2023-12-07 10:49
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class NotMeetingRecoveryQueryVO extends BasePage {
    @ApiModelProperty(value = "备注参数")
    private String remark;
}
