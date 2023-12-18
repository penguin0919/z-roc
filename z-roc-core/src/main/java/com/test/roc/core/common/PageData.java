package com.test.roc.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageData
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-10-19 11:40
 * @Version 1.0
 */
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class PageData<T> {

    @ApiModelProperty(value = "总页数")
    private long total;

    @ApiModelProperty(value = "数据列表")
    private List<T> rows;

    @ApiModelProperty(value = "是否最后一页")
    private Boolean isEnd;
}