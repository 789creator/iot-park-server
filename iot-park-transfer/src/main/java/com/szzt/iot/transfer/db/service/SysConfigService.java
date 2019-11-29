package com.szzt.iot.transfer.db.service;

import com.szzt.iot.common.service.CrudService;
import com.szzt.iot.transfer.db.dto.SysConfigDTO;
import com.szzt.iot.transfer.db.entity.SysConfigEntity;

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