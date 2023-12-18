package com.test.roc.controller;

import com.test.roc.core.common.BaseController;
import com.test.roc.core.common.Page;
import com.test.roc.core.common.Result;
import com.test.roc.model.dto.MeetingModifyDTO;
import com.test.roc.model.dto.MeetingQueryDTO;
import com.test.roc.model.entity.Meeting;
import com.test.roc.model.vo.MeetingModifyVO;
import com.test.roc.model.vo.MeetingQueryVO;
import com.test.roc.service.MeetingService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * 会议管理
 *
 * @author z-Roc
 * @date 2023-12-04 09:11
 **/
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("meeting")
@Api(tags = "会议管理")
public class MeetingController extends BaseController {
    @Autowired
    private MeetingService meetingService;

    @ApiOperation("新增会议")
    @PostMapping("/create")
    public Result<String> create(@Valid @RequestBody MeetingModifyVO createVo,
                                 @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        MeetingModifyDTO createDto = new MeetingModifyDTO();
        BeanUtils.copyProperties(createVo, createDto);
        meetingService.create(createDto);
        return Result.success();
    }

    @ApiOperation("删除会议")
    @ApiImplicitParam(name = "meetingId", value = "会议ID", required = true)
    @DeleteMapping("/delete/{meetingId}")
    public Result<String> delete(@PathVariable String meetingId) {
        meetingService.delete(meetingId);
        return Result.success();
    }

    @ApiOperation("修改会议")
    @ApiImplicitParam(name = "meetingId", value = "会议ID", required = true)
    @PostMapping("/update/{meetingId}")
    public Result<String> update(@PathVariable String meetingId,
                                 @Valid @RequestBody MeetingModifyVO updateVo,
                                 @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        MeetingModifyDTO updateDto = new MeetingModifyDTO();
        BeanUtils.copyProperties(updateVo, updateDto);
        updateDto.setId(meetingId);
        meetingService.update(updateDto);
        return Result.success();
    }

    @ApiOperation("查询会议列表")
    @PostMapping("/query")
    public Result<Page<Meeting>> query(@RequestBody MeetingQueryVO queryVo) {
        initPageHelper(queryVo);
        MeetingQueryDTO queryDto = new MeetingQueryDTO();
        BeanUtils.copyProperties(queryVo, queryDto);
        List<Meeting> meetingList = meetingService.queryMeetings(queryDto);
        Page<Meeting> page = new Page<>();
        page.initPage(meetingList, new PageInfo<>(meetingList).getTotal());
        return Result.success(page);
    }
}
