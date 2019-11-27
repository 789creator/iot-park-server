package com.szzt.iot.admin.modules.agent.controller;

import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorDTO;
import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorHistoryDTO;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorHistoryService;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;


/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
@RestController
@RequestMapping("agent/system/monitor")
@Api(tags = "系统监控表")
public class AgentSystemMonitorController {
    @Autowired
    private AgentSystemMonitorService agentSystemMonitorService;
    @Autowired
    private AgentSystemMonitorHistoryService agentSystemMonitorHistoryService;

    @GetMapping("page")
    @ApiOperation("分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query", required = true, dataType = "int"),
            @ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType = "String")
    })
    @RequiresPermissions("agent:systemMonitor:page")
    public Result<PageData<AgentSystemMonitorDTO>> page(@ApiIgnore @RequestParam Map<String, Object> params) {
        PageData<AgentSystemMonitorDTO> page = agentSystemMonitorService.page(params);

        return new Result<PageData<AgentSystemMonitorDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    @RequiresPermissions("agent:systemMonitor:info")
    public Result<AgentSystemMonitorDTO> get(@PathVariable("id") Long id) {

        AgentSystemMonitorDTO data = agentSystemMonitorService.get(id);

        return new Result<AgentSystemMonitorDTO>().ok(data);
    }

    @GetMapping("history")
    @ApiOperation("信息")
    @RequiresPermissions("agent:systemMonitor:info")
    public Result<PageData<AgentSystemMonitorHistoryDTO>> history(@RequestParam Map<String, Object> params) {
        PageData<AgentSystemMonitorHistoryDTO> page = agentSystemMonitorHistoryService.page(params);
        return new Result<PageData<AgentSystemMonitorHistoryDTO>>().ok(page);
    }

    /**
     * cpu 监控历史数据
     * @param params
     * @return
     */
    @GetMapping("cpu")
    @RequiresPermissions("agent:systemMonitor:info")
    public Result cpu(@RequestParam Map<String, Object> params) {
        return agentSystemMonitorHistoryService.pageCpu(params);
    }
    /**
     * 内存 监控历史数据
     * @param params
     * @return
     */
    @GetMapping("memory")
    @RequiresPermissions("agent:systemMonitor:info")
    public Result memory(@RequestParam Map<String, Object> params) {
        return agentSystemMonitorHistoryService.pageMemory(params);
    }

}