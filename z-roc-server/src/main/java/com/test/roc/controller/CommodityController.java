package com.test.roc.controller;

import com.test.roc.core.common.BaseController;
import com.test.roc.core.common.Page;
import com.test.roc.core.common.Result;
import com.test.roc.model.dto.CommodityModifyDTO;
import com.test.roc.model.dto.CommodityQueryDTO;
import com.test.roc.model.entity.Commodity;
import com.test.roc.model.vo.CommodityModifyVO;
import com.test.roc.model.vo.CommodityQueryVO;
import com.test.roc.service.CommodityService;
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
 * 回收商品controller
 *
 * @author z-Roc
 * @date 2023-12-04 14:15
 **/
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
@RequestMapping("commodity")
@Api(tags = "回收商品管理")
public class CommodityController extends BaseController {
    @Autowired
    private CommodityService commodityService;

    @ApiOperation("新增回收商品")
    @PostMapping("/create")
    public Result<String> create(@Valid @RequestBody CommodityModifyVO createVo,
                                 @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        CommodityModifyDTO createDto = new CommodityModifyDTO();
        BeanUtils.copyProperties(createVo, createDto);
        commodityService.create(createDto);
        return Result.success();
    }

    @ApiOperation("删除回收商品")
    @ApiImplicitParam(name = "commodityId", value = "新增回收ID", required = true)
    @DeleteMapping("/delete/{commodityId}")
    public Result<String> delete(@PathVariable String commodityId) {
        commodityService.delete(commodityId);
        return Result.success();
    }

    @ApiOperation("编辑回收商品")
    @ApiImplicitParam(name = "commodityId", value = "回收商品ID", required = true)
    @PostMapping("/update/{commodityId}")
    public Result<String> update(@PathVariable String commodityId,
                                 @Valid @RequestBody CommodityModifyVO updateVo,
                                 @ApiIgnore BindingResult bindingResult) {
        validationFields(bindingResult);
        CommodityModifyDTO updateDto = new CommodityModifyDTO();
        BeanUtils.copyProperties(updateVo, updateDto);
        updateDto.setId(commodityId);
        commodityService.update(updateDto);
        return Result.success();
    }

    @ApiOperation("查询回收商品列表")
    @PostMapping("/query")
    public Result<Page<Commodity>> query(@RequestBody CommodityQueryVO queryVo) {
        initPageHelper(queryVo);
        CommodityQueryDTO queryDto = new CommodityQueryDTO();
        BeanUtils.copyProperties(queryVo, queryDto);
        List<Commodity> commodityList = commodityService.queryCommodityList(queryDto);
        Page<Commodity> page = new Page<>();
        page.initPage(commodityList, new PageInfo<>(commodityList).getTotal());
        return Result.success(page);
    }
}
