package com.test.roc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.roc.model.bo.NotMeetingRecoveryInfoBO;
import com.test.roc.model.dto.*;
import com.test.roc.model.entity.Recovery;

import java.util.List;

/**
 * 回收流水service
 *
 * @author z-Roc
 * @date 2023-12-01 14:32
 **/
public interface RecoveryService extends IService<Recovery> {
    /**
     * 新增会议类型回收流水
     *
     * @param createDto 新增参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void createMeetingRecovery(MeetingRecoveryCreateDTO createDto);

    /**
     * 新增非会议类型回收流水
     *
     * @param createDto 新增参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void createNotMeetingRecovery(NotMeetingRecoveryCreateDTO createDto);

    /**
     * 删除回收流水
     *
     * @param recoveryId 流水ID
     * @author z-Roc
     * @date 2023/12/4
     **/
    void delete(String recoveryId);

    /**
     * 修改回收流水
     *
     * @param updateDto 修改参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void updateRecovery(RecoveryModifyDTO updateDto);

    /**
     * 我的流水明细
     *
     * @param queryDto 查询参数
     * @return com.test.assistant.model.dto.MyRecoveryPage
     * @author z-Roc
     * @date 2023/12/4
     **/
    MyRecoveryPageDTO myRecoveryPage(MyRecoveryQueryDTO queryDto);

    /**
     * 会议下的回收流水列表
     *
     * @param meetingId 会议ID
     * @return com.test.assistant.model.dto.MeetingRecoveryInfoDTO
     * @author z-Roc
     * @date 2023/12/7
     **/
    MeetingRecoveryInfoDTO meetingRecoveryList(String meetingId);

    /**
     * 非会议类型列表
     *
     * @param queryDto 查询参数
     * @return java.util.List<com.test.assistant.model.bo.NotMeetingRecoveryInfoBO>
     * @author z-Roc
     * @date 2023/12/7
     **/
    List<NotMeetingRecoveryInfoBO> notMeetingRecoveryList(NotMeetingRecoveryQueryDTO queryDto);
}
