package com.szzt.iot.admin.modules.device.controller;

import com.szzt.iot.admin.common.annotation.LogOperation;
import com.szzt.iot.admin.common.utils.ExcelUtils;
import com.szzt.iot.admin.modules.device.dto.DeviceSmsTemplateDTO;
import com.szzt.iot.admin.modules.device.excel.DeviceSmsTemplateExcel;
import com.szzt.iot.admin.modules.device.service.DeviceSmsTemplateService;
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
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
@RestController
@RequestMapping("device/sms/template")
@Api(tags = "告警设备通知内容模板")
public class DeviceSmsTemplateController {
    @Autowired
    private DeviceSmsTemplateService deviceSmsTemplateService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("device:sms:template:page")
    public Result<PageData<DeviceSmsTemplateDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<DeviceSmsTemplateDTO> page = deviceSmsTemplateService.page(params);

        return new Result<PageData<DeviceSmsTemplateDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("device:sms:template:info")
    public Result<DeviceSmsTemplateDTO> get(@PathVariable("id") Long id) {
        DeviceSmsTemplateDTO data = deviceSmsTemplateService.get(id);

        return new Result<DeviceSmsTemplateDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    @RequiresPermissions("device:sms:template:save")
    public Result save(@RequestBody DeviceSmsTemplateDTO dto) {
        //校验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
        dto.setIsDefault(0);
        deviceSmsTemplateService.save(dto);

        return new Result();
    }

    @PutMapping
    @ApiOperation("修改")
    @LogOperation("修改")
    @RequiresPermissions("device:sms:template:update")
    public Result update(@RequestBody DeviceSmsTemplateDTO dto) {
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        deviceSmsTemplateService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @ApiOperation("删除")
    @LogOperation("删除")
    @RequiresPermissions("device:sms:template:delete")
    public Result delete(@RequestBody Long[] ids) {
        //效验数据
        AssertUtils.isArrayEmpty(ids, "id");

        deviceSmsTemplateService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @ApiOperation("导出")
    @LogOperation("导出")
    @RequiresPermissions("device:sms:template:export")
    public void export(@ApiIgnore @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DeviceSmsTemplateDTO> list = deviceSmsTemplateService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, list, DeviceSmsTemplateExcel.class);
    }

    @GetMapping("setDefault/{id}")
    @ApiOperation("设置默认模板信息")
    @RequiresPermissions("device:sms:template:save")
    public Result setDefault(@PathVariable("id") Long id) {
        return deviceSmsTemplateService.setDefault(id);
    }

}