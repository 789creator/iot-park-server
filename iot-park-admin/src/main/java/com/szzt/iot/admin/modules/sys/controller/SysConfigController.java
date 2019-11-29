package com.szzt.iot.admin.modules.sys.controller;

import com.szzt.iot.admin.common.annotation.LogOperation;
import com.szzt.iot.admin.common.utils.ExcelUtils;
import com.szzt.iot.admin.modules.sys.dto.SysConfigDTO;
import com.szzt.iot.admin.modules.sys.excel.SysConfigExcel;
import com.szzt.iot.admin.modules.sys.service.SysConfigService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.utils.Result;
import com.szzt.iot.common.validator.AssertUtils;
import com.szzt.iot.common.validator.ValidatorUtils;
import com.szzt.iot.common.validator.group.AddGroup;
import com.szzt.iot.common.validator.group.DefaultGroup;
import com.szzt.iot.common.validator.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
@RestController
@RequestMapping("sys/config")
@Api(tags="系统配置")
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("sys:config:page")
    public Result<PageData<SysConfigDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<SysConfigDTO> page = sysConfigService.page(params);

        return new Result<PageData<SysConfigDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("sys:config:info")
    public Result<SysConfigDTO> get(@PathVariable("id") Long id){
        SysConfigDTO data = sysConfigService.get(id);

        return new Result<SysConfigDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("sys:config:save")
    public Result save(@RequestBody SysConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysConfigService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("sys:config:update")
    public Result update(@RequestBody SysConfigDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysConfigService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("sys:config:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        sysConfigService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("sys:config:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysConfigDTO> list = sysConfigService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, SysConfigExcel.class);
    }

}