package com.test.roc.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-10-19 11:48
 * @Version 1.0
 */
@Data
@ApiModel
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

    @ApiModelProperty(value = "错误码")
    private String code = ErrorCode.MYB_000000.getCode();

    @ApiModelProperty(value = "错误描述")
    private String msg = ErrorCode.MYB_000000.getMsg();

    @ApiModelProperty(value = "数据")
    private PageData<T> data;

    public PageResult(long total, List<T> rows){
        this.data = new PageData(total,rows,null);
    }
}
