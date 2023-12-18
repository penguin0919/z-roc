package com.test.roc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.roc.model.dto.CommodityModifyDTO;
import com.test.roc.model.dto.CommodityQueryDTO;
import com.test.roc.model.entity.Commodity;

import java.util.List;

/**
 * 商品回收service
 *
 * @author z-Roc
 * @date 2023-12-01 14:35
 **/
public interface CommodityService extends IService<Commodity> {
    /**
     * 新增回收商品
     *
     * @param createDto 创建参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void create(CommodityModifyDTO createDto);

    /**
     * 删除回收商品
     *
     * @param commodityId 会议ID
     * @author z-Roc
     * @date 2023/12/4
     **/
    void delete(String commodityId);

    /**
     * 修改回收商品
     *
     * @param updateDto 修改参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void update(CommodityModifyDTO updateDto);

    /**
     * 参训回收商品列表
     *
     * @param queryDto 参训参数
     * @return java.util.List<com.test.assistant.model.entity.Commodity>
     * @author z-Roc
     * @date 2023/12/4
     **/
    List<Commodity> queryCommodityList(CommodityQueryDTO queryDto);
}
