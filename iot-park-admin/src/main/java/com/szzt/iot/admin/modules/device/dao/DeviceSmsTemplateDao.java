package com.szzt.iot.admin.modules.device.dao;

import com.szzt.iot.admin.modules.device.entity.DeviceSmsTemplateEntity;
import com.szzt.iot.common.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
@Mapper
public interface DeviceSmsTemplateDao extends BaseDao<DeviceSmsTemplateEntity> {
    /**
     * 设置默认模板
     *
     * @param id
     */
    void setDefault(Long id);

    /**
     * 设置为非默认模板
     *
     * @param id
     */
    void setNotDefault(Long id);
}