/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.service.impl;

import com.szzt.iot.admin.modules.sys.dao.SysLanguageDao;
import com.szzt.iot.admin.modules.sys.entity.SysLanguageEntity;
import com.szzt.iot.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import com.szzt.iot.admin.modules.sys.service.SysLanguageService;

/**
 * 国际化
 *
 * @author
 */
@Service
public class SysLanguageServiceImpl extends BaseServiceImpl<SysLanguageDao, SysLanguageEntity> implements SysLanguageService {

    @Override
    public void saveOrUpdate(String tableName, Long tableId, String fieldName, String fieldValue, String language) {
        SysLanguageEntity entity = new SysLanguageEntity();
        entity.setTableName(tableName);
        entity.setTableId(tableId);
        entity.setFieldName(fieldName);
        entity.setFieldValue(fieldValue);
        entity.setLanguage(language);

        //判断是否有数据
        if(baseDao.getLanguage(entity) == null){
            baseDao.insert(entity);
        }else {
            baseDao.updateLanguage(entity);
        }
    }

    @Override
    public void deleteLanguage(String tableName, Long tableId) {
        baseDao.deleteLanguage(tableName, tableId);
    }
}