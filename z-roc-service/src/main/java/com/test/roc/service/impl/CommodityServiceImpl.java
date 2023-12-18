package com.test.roc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import com.test.roc.core.enmus.DelType;
import com.test.roc.core.enmus.OperateTypeEnum;
import com.test.roc.model.dto.CommodityModifyDTO;
import com.test.roc.model.dto.CommodityQueryDTO;
import com.test.roc.model.entity.Commodity;
import com.test.roc.repository.CommodityMapper;
import com.test.roc.service.CommodityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品回收service实现类
 *
 * @author z-Roc
 * @date 2023-12-01 14:36
 **/
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {
    @Override
    public void create(CommodityModifyDTO createDto) {
        // 校验重名
        if (checkCommodityNameRepeat(createDto.getName(), createDto.getMeetingId(), createDto.getId(), OperateTypeEnum.CREATE)) {
            throw new MyException(ErrorCode.COMMODITY_120100);
        }
        Commodity commodity = new Commodity().initModifyParams(createDto).createdBy("123");
        if (!save(commodity)) {
            throw new MyException(ErrorCode.COMMODITY_120100);
        }
    }

    @Override
    public void delete(String commodityId) {
        if (!removeById(commodityId)) {
            throw new MyException(ErrorCode.COMMODITY_120201);
        }
    }

    @Override
    public void update(CommodityModifyDTO updateDto) {
        // 校验重名
        if (checkCommodityNameRepeat(updateDto.getName(), updateDto.getMeetingId(), updateDto.getId(), OperateTypeEnum.UPDATE)) {
            throw new MyException(ErrorCode.COMMODITY_120100);
        }
        Commodity commodity = new Commodity().initModifyParams(updateDto);
        commodity.updatedBy("123");
        if (!updateById(commodity)) {
            throw new MyException(ErrorCode.COMMODITY_120301);
        }
    }

    @Override
    public List<Commodity> queryCommodityList(CommodityQueryDTO queryDto) {
        return list();
    }

    /**
     * 校验重命
     *
     * @param commodityName 回收商品名称
     * @param meetingId     会议ID
     * @param id            回收商品ID
     * @param operateType   操作类型
     * @return boolean
     * @author z-Roc
     * @date 2023/12/4
     **/
    private boolean checkCommodityNameRepeat(String commodityName, String meetingId, String id, OperateTypeEnum operateType) {
        LambdaQueryWrapper<Commodity> wrappers = new QueryWrapper<Commodity>().lambda();
        wrappers.eq(Commodity::getName, commodityName)
                .eq(Commodity::getIsDel, DelType.NO.getFlag())
                .eq(Commodity::getMeetingId, meetingId)
                // 修改时判断该条件
                .ne(operateType.equals(OperateTypeEnum.UPDATE), Commodity::getId, id);
        return count(wrappers) > 0;
    }
}
