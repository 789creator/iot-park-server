package com.szzt.iot.api.controller;


import com.szzt.iot.api.entity.DeviceSmokeEntity;
import com.szzt.iot.api.entity.Page;
import com.szzt.iot.api.service.SmokeDetectorService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@RestController
@RequestMapping("smoke")
@Api(tags = "烟感器的相关接口")
public class SmokeDetectorController {


    @Autowired
    private SmokeDetectorService smokeDetectorService;

    //查询烟感器分页列表
    @GetMapping("findSmoke")
    @ApiOperation(value = "分页",notes = "获取烟感器，参数当前页CurrentPage从1开始，PageSize每页多少条默认10条",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="page" , value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = "limit", value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
    })
    public Result<Page> findSmoke(@ApiIgnore@RequestParam Integer page , @ApiIgnore@RequestParam Integer limit) {
        Page page1 = smokeDetectorService.page(page, limit);

        return new Result<Page>().ok(page1);
    }
    //根据设备id查询设备状态为火警（4）的数据列表
    @GetMapping("selectSmokeStatus")
    @ApiOperation("设备状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
            @ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
            @ApiImplicitParam(name = "smokeName", value = "设备id", paramType = "query", dataType="String") ,
            @ApiImplicitParam(name = "smokeStatus", value = "设备状态", paramType = "query", dataType="String") ,
    })
    public Result<PageData<DeviceSmokeEntity>> selectSmokeStatus(@ApiIgnore @RequestParam Map<String,Object> param){
        PageData<DeviceSmokeEntity> page = smokeDetectorService.smokePage(param);
        return new Result<PageData<DeviceSmokeEntity>>().ok(page);
    }

}
