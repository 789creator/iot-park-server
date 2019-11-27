/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.log.service;

import com.szzt.iot.admin.modules.log.dto.SysLogOperationDTO;
import com.szzt.iot.admin.modules.log.entity.SysLogOperationEntity;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 操作日志
 *
 * @author
 * @since 1.0.0
 */
public interface SysLogOperationService extends BaseService<SysLogOperationEntity> {

    PageData<SysLogOperationDTO> page(Map<String, Object> params);

    List<SysLogOperationDTO> list(Map<String, Object> params);

    void save(SysLogOperationEntity entity);
}