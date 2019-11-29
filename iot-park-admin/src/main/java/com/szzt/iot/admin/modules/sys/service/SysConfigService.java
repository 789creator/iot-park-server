package com.szzt.iot.admin.modules.sys.service;

import com.szzt.iot.admin.modules.sys.dto.SysConfigDTO;
import com.szzt.iot.admin.modules.sys.entity.SysConfigEntity;
import com.szzt.iot.common.service.CrudService;

import java.util.Map;

/**
 * 系统配置
 *
 * @author szzt ${email}
 * @since 1.0.0 2019-11-29
 */
public interface SysConfigService extends CrudService<SysConfigEntity, SysConfigDTO> {
    /**
     * 获取获取配置
     * @return
     */
    Map getAllConfig();

}