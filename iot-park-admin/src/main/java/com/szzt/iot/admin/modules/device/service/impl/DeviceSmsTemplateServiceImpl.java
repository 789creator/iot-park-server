package com.szzt.iot.admin.modules.device.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szzt.iot.admin.modules.device.dao.DeviceSmsTemplateDao;
import com.szzt.iot.admin.modules.device.dto.DeviceSmsTemplateDTO;
import com.szzt.iot.admin.modules.device.entity.DeviceSmsTemplateEntity;
import com.szzt.iot.admin.modules.device.service.DeviceSmsTemplateService;
import com.szzt.iot.common.service.impl.CrudServiceImpl;
import com.szzt.iot.common.utils.ConvertUtils;
import com.szzt.iot.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 告警设备通知内容模板
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-10-28
 */
@Service
public class DeviceSmsTemplateServiceImpl extends CrudServiceImpl<DeviceSmsTemplateDao, DeviceSmsTemplateEntity, DeviceSmsTemplateDTO> implements DeviceSmsTemplateService {

    @Autowired
    private DeviceSmsTemplateDao deviceSmsTemplateDao;

    @Override
    public QueryWrapper<DeviceSmsTemplateEntity> getWrapper(Map<String, Object> params) {
        String id = (String) params.get("id");

        QueryWrapper<DeviceSmsTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public DeviceSmsTemplateDTO getDefaultTemplate() {
        QueryWrapper<DeviceSmsTemplateEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_default", 1);
        DeviceSmsTemplateEntity deviceSmsTemplateEntity = deviceSmsTemplateDao.selectOne(wrapper);
        return ConvertUtils.sourceToTarget(deviceSmsTemplateEntity, DeviceSmsTemplateDTO.class);
    }

    @Override
    public Result setDefault(Long id) {
        // 设置为非默认模板
        deviceSmsTemplateDao.setNotDefault(id);
        // 设置为默认模板
        deviceSmsTemplateDao.setDefault(id);
        return new Result();
    }
}