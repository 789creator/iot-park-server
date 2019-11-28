package com.szzt.iot.admin.modules.device.service;

import com.szzt.iot.admin.modules.device.dto.DeviceSmokeAlarmDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceSmokeAlarmEntity;
import com.szzt.iot.common.service.CrudService;

/**
 * 烟雾报警数据
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-28
 */
public interface DeviceSmokeAlarmService extends CrudService<DeviceSmokeAlarmEntity, DeviceSmokeAlarmDTO> {
    /**
     * 入库，接收到阿里云通过消息队列传递回来的消息
     * @param content
     */
    void saveDB(String content);
}