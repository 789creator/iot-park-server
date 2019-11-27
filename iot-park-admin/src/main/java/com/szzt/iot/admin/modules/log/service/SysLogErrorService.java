/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.log.service;


import com.szzt.iot.admin.modules.log.dto.SysLogErrorDTO;
import com.szzt.iot.admin.modules.log.entity.SysLogErrorEntity;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author
 * @since 1.0.0
 */
public interface SysLogErrorService extends BaseService<SysLogErrorEntity> {

    PageData<SysLogErrorDTO> page(Map<String, Object> params);

    List<SysLogErrorDTO> list(Map<String, Object> params);

    void save(SysLogErrorEntity entity);

}