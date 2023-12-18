package com.test.roc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.test.roc.core.common.RecordInfo;
import com.test.roc.core.util.IdUtils;
import com.test.roc.model.dto.CommodityModifyDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 物品管理
 *
 * @author z-Roc
 * @date 2023-11-30 17:16
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("回收商品表")
@TableName(value = "t_commodity")
public class Commodity extends RecordInfo<Commodity, String> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "回收商品名称")
    private String name;
    @ApiModelProperty(value = "回收商品照片路径")
    private String imgPath;
    @ApiModelProperty(value = "所属会议ID")
    private String meetingId;
    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 初始化编辑参数
     *
     * @param modify 参数
     * @return com.test.assistant.model.entity.Commodity
     * @author z-Roc
     * @date 2023/12/4
     **/
    public Commodity initModifyParams(CommodityModifyDTO modify) {
        this.id = StringUtils.isNotBlank(modify.getId()) ? modify.getId() : IdUtils.fastSimpleUUID();
        this.name = modify.getName();
        this.imgPath = modify.getImgPath();
        this.meetingId = modify.getMeetingId();
        this.integral = modify.getIntegral();
        this.remark = modify.getRemark();
        return this;
    }
}
