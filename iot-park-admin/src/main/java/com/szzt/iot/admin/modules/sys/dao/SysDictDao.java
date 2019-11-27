/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 *
 * https://www.szzt.com.cn
 *
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.sys.dao;

import com.szzt.iot.admin.modules.sys.entity.SysDictEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据字典
 *
 * @author
 */
@Mapper
public interface SysDictDao extends BaseDao<SysDictEntity> {

    /**
     * 修改字典类型
     * @param dictType  字典类型
     * @param pid       pid
     */
	void updateDictType(@Param("dictType") String dictType, @Param("pid") Long pid);

}
