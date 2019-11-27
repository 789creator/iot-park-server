package com.szzt.iot.admin.modules.device.service;

import com.szzt.iot.admin.modules.device.dto.DeviceNoticeSmsDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceNoticeSmsEntity;
import com.szzt.iot.common.service.CrudService;

/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
public interface DeviceNoticeSmsService extends CrudService<DeviceNoticeSmsEntity, DeviceNoticeSmsDTO> {

}