package com.test.roc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.test.roc.core.common.ErrorCode;
import com.test.roc.core.common.MyException;
import com.test.roc.core.enmus.DelType;
import com.test.roc.core.enmus.OperateTypeEnum;
import com.test.roc.model.dto.MeetingModifyDTO;
import com.test.roc.model.dto.MeetingQueryDTO;
import com.test.roc.model.entity.Meeting;
import com.test.roc.repository.MeetingMapper;
import com.test.roc.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会议service实现类
 *
 * @author z-Roc
 * @date 2023-12-01 14:34
 **/
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public void create(MeetingModifyDTO createDto) {
        // 校验重名
        if (checkMeetingNameRepeat(createDto.getName(), createDto.getId(), OperateTypeEnum.CREATE)) {
            throw new MyException(ErrorCode.MEETING_110100);
        }
        Meeting meeting = new Meeting().initModifyParams(createDto).createdBy("123");
        if (!save(meeting)) {
            throw new MyException(ErrorCode.MEETING_110100);
        }
    }

    @Override
    public void delete(String meetingId) {
        if (!removeById(meetingId)) {
            throw new MyException(ErrorCode.MEETING_110201);
        }
    }

    @Override
    public void update(MeetingModifyDTO createDto) {
        // 校验重名
        if (checkMeetingNameRepeat(createDto.getName(), createDto.getId(), OperateTypeEnum.UPDATE)) {
            throw new MyException(ErrorCode.MEETING_110100);
        }
        Meeting meeting = new Meeting().initModifyParams(createDto);
        meeting.updatedBy("123");
        if (!updateById(meeting)) {
            throw new MyException(ErrorCode.MEETING_110100);
        }
    }

    @Override
    public List<Meeting> queryMeetings(MeetingQueryDTO queryDto) {
        return meetingMapper.queryMeetingList(queryDto);
    }

    /**
     * 校验重命
     *
     * @param meetingName 会议名称
     * @param id          会议ID
     * @param operateType 操作类型
     * @return boolean
     * @author z-Roc
     * @date 2023/12/4
     **/
    private boolean checkMeetingNameRepeat(String meetingName, String id, OperateTypeEnum operateType) {
        LambdaQueryWrapper<Meeting> wrappers = new QueryWrapper<Meeting>().lambda();
        wrappers.eq(Meeting::getName, meetingName)
                .eq(Meeting::getIsDel, DelType.NO.getFlag())
                // 修改时判断该条件
                .ne(operateType.equals(OperateTypeEnum.UPDATE), Meeting::getId, id);
        return count(wrappers) > 0;
    }
}
