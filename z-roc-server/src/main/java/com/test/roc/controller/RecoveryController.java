package com.test.roc.controller;

import com.test.roc.core.common.BaseController;
import com.test.roc.core.common.Page;
import com.test.roc.core.common.Result;
import com.test.roc.model.bo.NotMeetingRecoveryInfoBO;
import com.test.roc.service.RecoveryService;
import com.github.pagehelper.PageInfo;
import com.test.roc.model.dto.*;
import com.test.roc.model.vo.*;
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
 * 回收流水Controller
 *
 * @author z-Roc
 * @date 2023-12-01 15:43
 **/
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("recovery")
@Api(tags = "回收明细")
public class RecoveryController extends BaseController {
    @Autowired
    private RecoveryService recoveryService;

    @ApiOperation("新增会议类型回收流水")
    @PostMapping("/create/meeting")
    public Result<String> createMeetingRecovery(@Valid @RequestBody MeetingRecoveryCreateVO createVo,
                                                @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        MeetingRecoveryCreateDTO createDto = new MeetingRecoveryCreateDTO();
        BeanUtils.copyProperties(createVo, createDto);
        recoveryService.createMeetingRecovery(createDto);
        return Result.success();
    }

    @ApiOperation("新增非会议类型回收流水")
    @PostMapping("/create/not/meeting")
    public Result<String> createNotMeetingRecovery(@Valid @RequestBody NotMeetingRecoveryCreateVO createVo,
                                                   @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        NotMeetingRecoveryCreateDTO createDto = new NotMeetingRecoveryCreateDTO();
        BeanUtils.copyProperties(createVo, createDto);
        recoveryService.createNotMeetingRecovery(createDto);
        return Result.success();
    }

    @ApiOperation("删除回收流水")
    @ApiImplicitParam(name = "recoveryId", value = "回收流水ID", required = true)
    @DeleteMapping("/delete/{recoveryId}")
    public Result<String> delete(@PathVariable String recoveryId) {
        recoveryService.delete(recoveryId);
        return Result.success();
    }

    @ApiOperation("修改型回收流水")
    @PostMapping("/update/{recoveryId}")
    public Result<String> updateNotMeetingRecovery(@PathVariable String recoveryId,
                                                   @Valid @RequestBody RecoveryModifyVO updateVo,
                                                   @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        RecoveryModifyDTO updateDto = new RecoveryModifyDTO();
        BeanUtils.copyProperties(updateVo, updateDto);
        updateDto.setId(recoveryId);
        recoveryService.updateRecovery(updateDto);
        return Result.success();
    }

    @ApiOperation("我的回收流水")
    @PostMapping("/my/recovery")
    public Result<MyRecoveryPageDTO> myRecoveryPage(@RequestBody MyRecoveryQueryVO queryVo) {
        initPageHelper(queryVo);
        MyRecoveryQueryDTO queryDto = new MyRecoveryQueryDTO();
        BeanUtils.copyProperties(queryVo, queryDto);
        return Result.success(recoveryService.myRecoveryPage(queryDto));
    }

    @ApiOperation("会议下的回收流水详情")
    @ApiImplicitParam(name = "meetingId", value = "会议ID", required = true)
    @GetMapping("/meeting/recovery/list/{meetingId}")
    public Result<MeetingRecoveryInfoDTO> meetingRecoveryList(@PathVariable String meetingId) {
        return Result.success(recoveryService.meetingRecoveryList(meetingId));
    }

    @ApiOperation("非会议下的回收流水详情")
    @PostMapping("/not/meeting/recovery/list")
    public Result<Page<NotMeetingRecoveryInfoBO>> notMeetingRecoveryList(@RequestBody NotMeetingRecoveryQueryVO queryVo) {
        initPageHelper(queryVo);
        NotMeetingRecoveryQueryDTO queryDto = new NotMeetingRecoveryQueryDTO();
        BeanUtils.copyProperties(queryVo, queryDto);
        Page<NotMeetingRecoveryInfoBO> page = new Page<>();
        List<NotMeetingRecoveryInfoBO> recoveryInfoList = recoveryService.notMeetingRecoveryList(queryDto);
        page.initPage(recoveryInfoList, new PageInfo<>(recoveryInfoList).getTotal());
        return Result.success(page);
    }
}
