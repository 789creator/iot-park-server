/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.demo.service;

import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.BaseService;
import com.szzt.iot.admin.modules.demo.dto.NewsDTO;
import com.szzt.iot.admin.modules.demo.entity.NewsEntity;

import java.util.Map;

/**
 * 新闻
 *
 * @author
 */
public interface NewsService extends BaseService<NewsEntity> {

    PageData<NewsDTO> page(Map<String, Object> params);

    NewsDTO get(Long id);

    void save(NewsDTO dto);

    void update(NewsDTO dto);
}

