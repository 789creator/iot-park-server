package com.szzt.iot.admin.modules.device.service;

import com.szzt.iot.admin.modules.device.dto.DeviceSmsTemplateDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceSmsTemplateEntity;
import com.szzt.iot.common.service.CrudService;
import com.szzt.iot.common.utils.Result;

/**
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
public interface DeviceSmsTemplateService extends CrudService<DeviceSmsTemplateEntity, DeviceSmsTemplateDTO> {

    /**
     * 获取默认的模板信息内容
     *
     * @return
     */
    DeviceSmsTemplateDTO getDefaultTemplate();

    /**
     * 设置默认模板信息
     *
     * @param id
     * @return
     */
    Result setDefault(Long id);
}