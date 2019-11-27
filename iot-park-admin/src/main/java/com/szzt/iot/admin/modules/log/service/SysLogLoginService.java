/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.log.service;

import com.szzt.iot.admin.modules.log.dto.SysLogLoginDTO;
import com.szzt.iot.admin.modules.log.entity.SysLogLoginEntity;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 *
 * @author
 * @since 1.0.0
 */
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {

    PageData<SysLogLoginDTO> page(Map<String, Object> params);

    List<SysLogLoginDTO> list(Map<String, Object> params);

    void save(SysLogLoginEntity entity);
}