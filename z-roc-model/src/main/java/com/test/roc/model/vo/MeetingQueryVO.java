package com.test.roc.model.vo;

import com.test.roc.core.common.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**

 * 会议查询参数
 * @author z-Roc
 * @date 2023-12-04 10:31
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class MeetingQueryVO extends BasePage {
    @ApiModelProperty(value = "会议名称")
    private String name;
}
