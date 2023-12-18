package com.test.roc.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.roc.model.dto.MeetingQueryDTO;
import com.test.roc.model.entity.Meeting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 会议mapper
 *
 * @author z-Roc
 * @date 2023-12-01 14:27
 **/
@Component
public interface MeetingMapper extends BaseMapper<Meeting> {
    /**
     * 參訓会议列表
     *
     * @param query 參訓參數
     * @return java.util.List<com.test.assistant.model.bo.Meeting>
     * @author z-Roc
     * @date 2023/12/4
     **/
    List<Meeting> queryMeetingList(@Param("query") MeetingQueryDTO query);
}
