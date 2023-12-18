package com.test.roc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import com.test.roc.core.enmus.RecoveryType;
import com.test.roc.model.bo.NotMeetingRecoveryInfoBO;
import com.test.roc.model.dto.*;
import com.test.roc.model.entity.Recovery;
import com.test.roc.repository.RecoveryMapper;
import com.test.roc.service.RecoveryService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 回收流水service实现类
 *
 * @author z-Roc
 * @date 2023-12-01 14:32
 **/
@Service
public class RecoveryServiceImpl extends ServiceImpl<RecoveryMapper, Recovery> implements RecoveryService {
    @Resource
    private RecoveryMapper recoveryMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createMeetingRecovery(MeetingRecoveryCreateDTO createDto) {
        Recovery clientRecovery = new Recovery().initMeetingRecoveryParams(createDto).createdBy("123");
        clientRecovery.setType(RecoveryType.CLIENT);
        Recovery manageRecovery = new Recovery().initMeetingRecoveryParams(createDto).createdBy("123");
        manageRecovery.setType(RecoveryType.MANAGE);
        // 同时保存两端的数据
        save(clientRecovery);
        save(manageRecovery);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createNotMeetingRecovery(NotMeetingRecoveryCreateDTO createDto) {
        Recovery clientRecovery = new Recovery().initNotMeetingRecoveryParams(createDto).createdBy("123");
        clientRecovery.setType(RecoveryType.CLIENT);
        Recovery manageRecovery = new Recovery().initNotMeetingRecoveryParams(createDto).createdBy("123");
        manageRecovery.setType(RecoveryType.MANAGE);
        // 同时保存两端的数据
        save(clientRecovery);
        save(manageRecovery);
    }

    @Override
    public void delete(String recoveryId) {
        Recovery recovery = getById(recoveryId);
        if (Objects.isNull(recovery)) {
            throw new MyException(ErrorCode.RECOVERY_130501);
        }
        if (!removeById(recoveryId)) {
            throw new MyException(ErrorCode.RECOVERY_130201);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRecovery(RecoveryModifyDTO updateDto) {
        Recovery recovery = getById(updateDto.getId());
        if (Objects.isNull(recovery)) {
            throw new MyException(ErrorCode.RECOVERY_130501);
        }
        // 客户端类型不可修改
        if (recovery.getType() == RecoveryType.CLIENT) {
            throw new MyException(ErrorCode.RECOVERY_130302);
        }
        recovery.initUpdateParams(updateDto);
        recovery.updatedBy("123");
        if (!updateById(recovery)) {
            throw new MyException(ErrorCode.RECOVERY_130301);
        }
    }

    @Override
    public MyRecoveryPageDTO myRecoveryPage(MyRecoveryQueryDTO queryDto) {
        MyRecoveryPageDTO recoveryPage = new MyRecoveryPageDTO();
        // 获取流水明细
        List<Recovery> recoveryList = recoveryMapper.selectMyRecoveryList("123");
        recoveryPage.initPage(recoveryList, new PageInfo<>(recoveryList).getTotal());
        // 根据用户, 获取总积分
        IntegralQueryDTO queryParams = new IntegralQueryDTO().statisticsByUid("123");
        recoveryPage.setMyIntegralTotal(recoveryMapper.selectTotalIntegral(queryParams));
        return recoveryPage;
    }

    @Override
    public MeetingRecoveryInfoDTO meetingRecoveryList(String meetingId) {
        MeetingRecoveryInfoDTO info = new MeetingRecoveryInfoDTO();
        // 流水列表
        info.setRecoveryList(recoveryMapper.selectRecoveryByMeetingId(meetingId));
        // 根据会议, 获取总积分
        IntegralQueryDTO queryParams = new IntegralQueryDTO().statisticsByMeeting(meetingId);
        info.setTotalIntegral(recoveryMapper.selectTotalIntegral(queryParams));
        return info;
    }

    @Override
    public List<NotMeetingRecoveryInfoBO> notMeetingRecoveryList(NotMeetingRecoveryQueryDTO queryDto) {
        return recoveryMapper.selectNotMeetingRecoveryList(queryDto);
    }
}
