package com.test.roc.model.entity.test;

import com.test.roc.core.common.RecordInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 测试
 *
 * @author z-Roc
 * @date 2023-12-15 16:39
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("测试")
public class Test extends RecordInfo<Test, Long> {
    @ApiModelProperty(value = "主键")
    private Long id;
}
