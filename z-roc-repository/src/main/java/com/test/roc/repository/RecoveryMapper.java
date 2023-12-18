package com.test.roc.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.roc.model.bo.NotMeetingRecoveryInfoBO;
import com.test.roc.model.dto.IntegralQueryDTO;
import com.test.roc.model.dto.NotMeetingRecoveryQueryDTO;
import com.test.roc.model.entity.Recovery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 回收流水Mapper
 *
 * @author z-Roc
 * @date 2023-12-01 14:30
 **/
@Component
public interface RecoveryMapper extends BaseMapper<Recovery> {
    /**
     * 查询我的流水
     *
     * @param uid 用户ID
     * @return java.util.List<com.test.assistant.model.entity.Recovery>
     * @author z-Roc
     * @date 2023/12/4
     **/
    List<Recovery> selectMyRecoveryList(@Param("uid") String uid);

    /**
     * 查询跟人总积分
     *
     * @param queryParams 查询参数
     * @return java.lang.String
     * @author z-Roc
     * @date 2023/12/4
     **/
    String selectTotalIntegral(@Param("queryParams") IntegralQueryDTO queryParams);

    /**
     * 根据会议ID查询回收列表
     *
     * @param meetingId 会议ID
     * @return java.util.List<com.test.assistant.model.entity.Recovery>
     * @author z-Roc
     * @date 2023/12/7
     **/
    List<Recovery> selectRecoveryByMeetingId(@Param("meetingId") String meetingId);

    /**
     * 非会议类型查询列表
     *
     * @param queryParams 查询参数
     * @return java.util.List<com.test.assistant.model.bo.NotMeetingRecoveryInfoBO>
     * @author z-Roc
     * @date 2023/12/7
     **/
    List<NotMeetingRecoveryInfoBO> selectNotMeetingRecoveryList(@Param("queryParams") NotMeetingRecoveryQueryDTO queryParams);
}
