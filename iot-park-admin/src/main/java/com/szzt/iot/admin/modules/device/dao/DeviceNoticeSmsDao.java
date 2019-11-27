package com.szzt.iot.admin.modules.device.dao;

import com.szzt.iot.admin.modules.device.entity.DeviceNoticeSmsEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Mapper
public interface DeviceNoticeSmsDao extends BaseDao<DeviceNoticeSmsEntity> {
	
}