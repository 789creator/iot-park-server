/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.service;

import com.szzt.iot.admin.modules.sys.dto.SysDeptDTO;
import com.szzt.iot.admin.modules.sys.entity.SysDeptEntity;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author
 */
public interface SysDeptService extends BaseService<SysDeptEntity> {

	List<SysDeptDTO> list(Map<String, Object> params);

	SysDeptDTO get(Long id);

	void save(SysDeptDTO dto);

	void update(SysDeptDTO dto);

	void delete(Long id);

	/**
	 * 根据部门ID，获取本部门及子部门ID列表
	 * @param id   部门ID
	 */
	List<Long> getSubDeptIdList(Long id);
}