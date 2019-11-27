/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.admin.common.annotation.LogOperation;
import com.szzt.iot.admin.modules.sys.dto.ServerStatusDTO;
import com.szzt.iot.admin.modules.sys.dto.ServersDTO;
import com.szzt.iot.admin.modules.sys.entity.AgentServerEntity;
import com.szzt.iot.admin.modules.sys.service.AgentServerService;
import com.szzt.iot.common.utils.Result;
import com.szzt.iot.common.validator.AssertUtils;
import com.szzt.iot.common.validator.ValidatorUtils;
import com.szzt.iot.common.validator.group.AddGroup;
import com.szzt.iot.common.validator.group.DefaultGroup;
import com.szzt.iot.common.validator.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务器控制器
 *
 * @author
 */
@RestController
@RequestMapping("/sys/agentServer")
@Api(tags="服务器管理")
public class ServersController {
    @Autowired
    private AgentServerService agentServerService;

    @RequestMapping("/list")
    @ApiOperation("列表")
    public Result<IPage<AgentServerEntity>> list(@RequestParam("status") String status,@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false)Integer pageNo){
        Map<String, Object> params = new HashMap<>();
        params.put("status", status);
        params.put("pageSize", pageSize);
        params.put("pageNo", pageNo);
        IPage<AgentServerEntity> res = agentServerService.list(params);

        return new Result<IPage<AgentServerEntity>>().ok(res);
    }

    @RequestMapping("/getServerStatus")
    @ApiOperation("获取状态")
    public Result<List<ServerStatusDTO>> getServerStatus(){
        List<ServerStatusDTO> list = agentServerService.getServerStatus();

        return new Result<List<ServerStatusDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @ApiOperation("信息")
    public Result<ServersDTO> get(@PathVariable("id") Long id){
        ServersDTO data = agentServerService.get(id);

        return new Result<ServersDTO>().ok(data);
    }

    @PostMapping
    @ApiOperation("保存")
    @LogOperation("保存")
    public Result save(@RequestBody ServersDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        agentServerService.save(dto);

        return new Result();
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    @LogOperation("修改")
    public Result update(@RequestBody ServersDTO dto){
        //效验数据
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        agentServerService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除")
    @LogOperation("删除")
    public Result delete(@PathVariable("id") Long id){
        //效验数据
        AssertUtils.isNull(id, "id");

        agentServerService.delete(id);

        return new Result();
    }

}