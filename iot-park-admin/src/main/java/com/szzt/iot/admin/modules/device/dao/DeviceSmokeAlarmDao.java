package com.szzt.iot.admin.modules.device.dao;

import com.szzt.iot.admin.modules.device.entity.DeviceSmokeAlarmEntity;
import com.szzt.iot.common.dao.BaseDao;
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