/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.service;

import com.szzt.iot.admin.modules.sys.dto.SysDictDTO;
import com.szzt.iot.admin.modules.sys.entity.SysDictEntity;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典
 *
 * @author
 * @since 1.0.0
 */
public interface SysDictService extends BaseService<SysDictEntity> {

    PageData<SysDictDTO> page(Map<String, Object> params);

    List<SysDictDTO> list(Map<String, Object> params);

    SysDictDTO get(Long id);

    void save(SysDictDTO dto);

    void update(SysDictDTO dto);

    void delete(Long[] ids);
}