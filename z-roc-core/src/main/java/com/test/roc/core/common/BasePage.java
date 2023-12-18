package com.test.roc.core.common;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName BasePage
 * @Description TODO
 * @Author cosmo
 * @DATE 2023-10-19 11:39
 * @Version 1.0
 */
@SuppressWarnings("unused")
@Data
@ApiModel(value = "分页基础类")
public class BasePage {
    @ApiModelProperty(value = "当前页码",required = true)
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页数量",required = true)
    private Integer pageSize = 10;


    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            pageSize = 10;
        }
        this.pageSize = pageSize;
    }
}
