package com.szzt.iot.transfer.db.service;

import com.szzt.iot.common.service.CrudService;
import com.szzt.iot.transfer.db.dto.DeviceSmokeAlarmDTO;
import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;

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