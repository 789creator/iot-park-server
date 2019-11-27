/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.api.service;

import com.szzt.iot.common.service.BaseService;
import com.szzt.iot.api.entity.UserEntity;
import com.szzt.iot.api.dto.LoginDTO;

import java.util.Map;

/**
 * 用户
 * 
 * @author
 */
public interface UserService extends BaseService<UserEntity> {

	UserEntity getByMobile(String mobile);

	UserEntity getUserByUserId(Long userId);

	/**
	 * 用户登录
	 * @param dto    登录表单
	 * @return        返回登录信息
	 */
	Map<String, Object> login(LoginDTO dto);
}
