package com.szzt.iot.admin.modules.agent.service;


import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorDTO;
import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorEntity;
import com.szzt.iot.common.activemq.system.SystemMonitorBody;
import com.szzt.iot.common.service.CrudService;

/**
 * 系统监控表（会定时删除这个表的数据）
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
public interface AgentSystemMonitorService extends CrudService<AgentSystemMonitorEntity, AgentSystemMonitorDTO> {

    /**
     * 更新状态agent服务器的实时状态
     *
     * @param body
     */
    void updateAgent(SystemMonitorBody body);
}