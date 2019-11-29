package com.szzt.iot.transfer.db.dao;

import com.szzt.iot.common.dao.BaseDao;
import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
@Mapper
public interface DeviceSmokeAlarmDao extends BaseDao<DeviceSmokeAlarmEntity> {
	
}