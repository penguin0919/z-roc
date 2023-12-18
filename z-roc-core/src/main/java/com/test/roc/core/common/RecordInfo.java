package com.test.roc.core.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 操作记录
 *
 * @author z-Roc
 * @date 2020-10-23 11:03
 */
@Data
public class RecordInfo<T, E> {
    @ApiModelProperty(value = "是否删除")
    @JSONField(serialize = false)
    @TableLogic(value = "0", delval = "1")
    Integer isDel;
    @ApiModelProperty(value = "创建人ID")
    @JSONField(serialize = false)
    E createId;

    @ApiModelProperty(value = "创建时间")
    @JSONField(serialize = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date createTime;

    @ApiModelProperty(value = "修改人ID")
    @JSONField(serialize = false)
    E updateId;

    @ApiModelProperty(value = "修改时间")
    @JSONField(serialize = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date updateTime;

    @SuppressWarnings("unchecked")
    public T createdBy(E userId) {
        Date date = new Date();
        this.createId = userId;
        this.createTime = date;
        // 新增时同步填充修改时间、修改人, 和创建人、创建时间一致
        this.updateId = userId;
        this.updateTime = date;
        return (T) this;
    }

    public void updatedBy(E userId) {
        this.updateId = userId;
        this.updateTime = new Date();
    }
}
