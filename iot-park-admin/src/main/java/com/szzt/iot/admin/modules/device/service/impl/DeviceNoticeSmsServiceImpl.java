package com.szzt.iot.admin.modules.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.admin.modules.device.dao.DeviceNoticeSmsDao;
import com.szzt.iot.admin.modules.device.dto.DeviceNoticeSmsDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceNoticeSmsEntity;
import com.szzt.iot.admin.modules.device.service.DeviceNoticeSmsService;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 告警设备短信接收记录
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-23
 */
@Service
public class DeviceNoticeSmsServiceImpl extends CrudServiceImpl<DeviceNoticeSmsDao, DeviceNoticeSmsEntity, DeviceNoticeSmsDTO> implements DeviceNoticeSmsService {

    @Override
    public QueryWrapper<DeviceNoticeSmsEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<DeviceNoticeSmsEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}