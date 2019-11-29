package com.szzt.iot.transfer.db.dao;

import com.szzt.iot.common.dao.BaseDao;
import com.szzt.iot.transfer.db.entity.SysConfigEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
@Mapper
public interface SysConfigDao extends BaseDao<SysConfigEntity> {
	
}