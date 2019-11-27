/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.admin.modules.log.dao.SysLogErrorDao;
import com.szzt.iot.admin.modules.log.dto.SysLogErrorDTO;
import com.szzt.iot.admin.modules.log.entity.SysLogErrorEntity;
import com.szzt.iot.admin.modules.log.service.SysLogErrorService;
import com.szzt.iot.common.constant.Constant;
import com.szzt.iot.common.page.PageData;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 异常日志
 *
 * @author
 * @since 1.0.0
 */
@Service
public class SysLogErrorServiceImpl extends BaseServiceImpl<SysLogErrorDao, SysLogErrorEntity> implements SysLogErrorService {

    @Override
    public PageData<SysLogErrorDTO> page(Map<String, Object> params) {
        IPage<SysLogErrorEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogErrorDTO.class);
    }

    @Override
    public List<SysLogErrorDTO> list(Map<String, Object> params) {
        List<SysLogErrorEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogErrorDTO.class);
    }

    private QueryWrapper<SysLogErrorEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<SysLogErrorEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysLogErrorEntity entity) {
        insert(entity);
    }

}