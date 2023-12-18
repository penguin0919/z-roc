package com.test.roc.core.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页page
 *
 * @author z-Roc
 * @date 2017-09-11
 */
@SuppressWarnings("unued")
@Data
@ApiModel("分页参数")
public class Page<T> {
    @ApiModelProperty(value = "数据列表")
    List<T> rows = new ArrayList<>();

    @ApiModelProperty(value = "总记录数", example = "88")
    long total = 0;

    public Page() {
        super();
    }

    public Page(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public void initPage(List<T> rows, long total) {
        if (ObjectUtils.isEmpty(rows)) {
            rows = Collections.emptyList();
        }
        this.rows = rows;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Page{" +
                "rows=" + rows +
                ", total=" + total +
                '}';
    }
}
