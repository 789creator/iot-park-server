/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.admin.modules.demo.service.NewsService;
import com.szzt.iot.admin.common.annotation.DataFilter;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import com.szzt.iot.admin.modules.demo.dao.NewsDao;
import com.szzt.iot.admin.modules.demo.dto.NewsDTO;
import com.szzt.iot.admin.modules.demo.entity.NewsEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NewsServiceImpl extends BaseServiceImpl<NewsDao, NewsEntity> implements NewsService {

    /**
     * mybatis数据权限演示
     */
    @Override
    @DataFilter(prefix = "and")
    public PageData<NewsDTO> page(Map<String, Object> params) {
        paramsToLike(params, "title");

        //分页
        IPage<NewsEntity> page = getPage(params, Constant.CREATE_DATE, false);

        //查询
        List<NewsEntity> list = baseDao.getList(params);

        return getPageData(list, page.getTotal(), NewsDTO.class);
    }

//    /**
//     * mybatis-plus数据权限演示
//     */
//    @Override
//    @DataFilter
//    public PageData<NewsDTO> page(Map<String, Object> params) {
//        IPage<NewsEntity> page = baseDao.selectPage(
//            getPage(params, Constant.CREATE_DATE, false),
//            getWrapper(params)
//        );
//        return getPageData(page, NewsDTO.class);
//    }
//
//    private QueryWrapper<NewsEntity> getWrapper(Map<String, Object> params){
//        String title = (String)params.get("title");
//        String startDate = (String)params.get("startDate");
//        String endDate = (String)params.get("endDate");
//
//        QueryWrapper<NewsEntity> wrapper = new QueryWrapper<>();
//        wrapper.like(StringUtils.isNotBlank(title), "title", title);
//        wrapper.ge(StringUtils.isNotBlank(startDate),"pub_date", startDate);
//        wrapper.le(StringUtils.isNotBlank(endDate),"pub_date", endDate);
//
//        //数据过滤
//        wrapper.apply(params.get(Constant.SQL_FILTER) != null, params.get(Constant.SQL_FILTER).toString());
//
//        return wrapper;
//    }

    @Override
    public NewsDTO get(Long id) {
        NewsEntity entity = baseDao.selectById(id);

        return ConvertUtils.sourceToTarget(entity, NewsDTO.class);
    }

    @Override
    public void save(NewsDTO dto) {
        NewsEntity entity = ConvertUtils.sourceToTarget(dto, NewsEntity.class);

        insert(entity);
    }

    @Override
    public void update(NewsDTO dto) {
        NewsEntity entity = ConvertUtils.sourceToTarget(dto, NewsEntity.class);

        updateById(entity);
    }

}
