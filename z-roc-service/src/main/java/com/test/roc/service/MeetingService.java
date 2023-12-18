package com.test.roc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.roc.model.dto.MeetingModifyDTO;
import com.test.roc.model.dto.MeetingQueryDTO;
import com.test.roc.model.entity.Meeting;

import java.util.List;

/**

 * 会议service
 * @author z-Roc
 * @date 2023-12-01 14:34
 **/
public interface MeetingService extends IService<Meeting> {
    /**
     * 创建会议
     *
     * @param createDto 创建参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void create(MeetingModifyDTO createDto);

    /**
     * 删除会议
     *
     * @param meetingId 会议ID
     * @author z-Roc
     * @date 2023/12/4
     **/
    void delete(String meetingId);

    /**
     * 修改会议
     *
     * @param createDto 修改参数
     * @author z-Roc
     * @date 2023/12/4
     **/
    void update(MeetingModifyDTO createDto);

    /**
     * 查询会议列表
     *
     * @param queryDto 查询参数
     * @return java.util.List<com.test.assistant.model.bo.Meeting>
     * @author z-Roc
     * @date 2023/12/4
     **/
    List<Meeting> queryMeetings(MeetingQueryDTO queryDto);
}
