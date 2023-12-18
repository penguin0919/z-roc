package com.test.roc.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 积分查询参数
 *
 * @author z-Roc
 * @date 2023-12-07 09:39
 **/
@Data
public class IntegralQueryDTO {
    @ApiModelProperty(value = "根据用户ID统计")
    private String uid;
    @ApiModelProperty(value = "根据会议ID统计")
    private String meetingId;

    /**
     * 根据用户统计
     *
     * @param uid 用户ID
     * @return com.test.assistant.model.dto.IntegralQueryParamsDto
     * @author z-Roc
     * @date 2023/12/7
     **/
    public IntegralQueryDTO statisticsByUid(String uid) {
        this.uid = uid;
        return this;
    }

    /**
     * 根据会议统计
     *
     * @param meetingId 会议ID
     * @return com.test.assistant.model.dto.IntegralQueryParamsDto
     * @author z-Roc
     * @date 2023/12/7
     **/
    public IntegralQueryDTO statisticsByMeeting(String meetingId) {
        this.meetingId = meetingId;
        return this;
    }
}
