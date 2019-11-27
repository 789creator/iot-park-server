/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.security.service;

import com.szzt.iot.admin.modules.security.entity.SysUserTokenEntity;
import com.szzt.iot.common.service.BaseService;
import com.szzt.iot.common.utils.Result;

/**
 * 用户Token
 * 
 * @author
 */
public interface SysUserTokenService extends BaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	Result createToken(Long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(Long userId);

}