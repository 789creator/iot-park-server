package com.szzt.iot.admin.modules.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.admin.modules.device.dao.DeviceNoticeResultDao;
import com.szzt.iot.admin.modules.device.dto.DeviceNoticeResultDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceNoticeResultEntity;
import com.szzt.iot.admin.modules.device.service.DeviceNoticeResultService;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-17
 */
@Service
public class DeviceNoticeResultServiceImpl extends CrudServiceImpl<DeviceNoticeResultDao, DeviceNoticeResultEntity, DeviceNoticeResultDTO> implements DeviceNoticeResultService {

    @Override
    public QueryWrapper<DeviceNoticeResultEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<DeviceNoticeResultEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


}