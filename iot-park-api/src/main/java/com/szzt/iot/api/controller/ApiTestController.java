/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.api.controller;

import com.szzt.iot.api.annotation.Login;
import com.szzt.iot.api.annotation.LoginUser;
import com.szzt.iot.api.entity.UserEntity;
import com.szzt.iot.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 测试接口
 *
 * @author
 */
@RestController
@RequestMapping("/api")
@Api(tags="测试接口")
public class ApiTestController {

    @Login
    @GetMapping("userInfo")
    @ApiOperation(value="获取用户信息", response=UserEntity.class)
    public Result<UserEntity> userInfo(@ApiIgnore @LoginUser UserEntity user){
        return new Result<UserEntity>().ok(user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public Result<Long> userInfo(@ApiIgnore @RequestAttribute("userId") Long userId){
        return new Result<Long>().ok(userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public Result<String> notToken(){
        return new Result<String>().ok("无需token也能访问。。。");
    }

}