package com.szzt.iot.admin.modules.device.controller;

import com.szzt.iot.admin.common.annotation.LogOperation;
import com.szzt.iot.admin.common.utils.ExcelUtils;
import com.szzt.iot.admin.modules.device.dto.DeviceNoticeSmsDTO;
import com.szzt.iot.admin.modules.device.excel.DeviceNoticeSmsExcel;
import com.szzt.iot.admin.modules.device.service.DeviceNoticeSmsService;
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
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@RestController
@RequestMapping("device/notice/sms")
@Api(tags="告警设备短信接收记录")
public class DeviceNoticeSmsController {
    @Autowired
    private DeviceNoticeSmsService deviceNoticeSmsService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
        @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
        @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
        @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String")
    })
    @RequiresPermissions("device:notice:sms:page")
    public Result<PageData<DeviceNoticeSmsDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params){
        PageData<DeviceNoticeSmsDTO> page = deviceNoticeSmsService.page(params);

        return new Result<PageData<DeviceNoticeSmsDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("device:notice:sms:info")
    public Result<DeviceNoticeSmsDTO> get(@PathVariable("id") Long id){
        DeviceNoticeSmsDTO data = deviceNoticeSmsService.get(id);

        return new Result<DeviceNoticeSmsDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("device:notice:sms:save")
    public Result save(@RequestBody DeviceNoticeSmsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        deviceNoticeSmsService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("device:notice:sms:update")
    public Result update(@RequestBody DeviceNoticeSmsDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        deviceNoticeSmsService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("device:notice:sms:delete")
    public Result delete(@RequestBody Long[] ids){
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        deviceNoticeSmsService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("device:notice:sms:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DeviceNoticeSmsDTO> list = deviceNoticeSmsService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, DeviceNoticeSmsExcel.class);
    }

}