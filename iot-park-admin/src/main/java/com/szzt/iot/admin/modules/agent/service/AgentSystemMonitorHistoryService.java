package com.szzt.iot.admin.modules.agent.service;


import com.szzt.iot.admin.modules.agent.dto.AgentSystemMonitorHistoryDTO;
import com.szzt.iot.admin.modules.agent.entity.AgentSystemMonitorHistoryEntity;
import com.szzt.iot.common.activemq.system.SystemMonitorBody;
import com.szzt.iot.common.service.CrudService;
import com.szzt.iot.common.utils.Result;

import java.util.Map;

/**
 * 系统监控历史记录
 *
 * @author szzt szzt@szzt.com
 * @since 1.0.0 2019-07-17
 */
public interface AgentSystemMonitorHistoryService extends CrudService<AgentSystemMonitorHistoryEntity, AgentSystemMonitorHistoryDTO> {
    /**
     * 监控到的数据入库
     *
     * @param body
     */
    void saveSystemMonitor(SystemMonitorBody body);

    /**
     * 删除数据库记录创建日期小于date的数据
     *
     * @param date
     */
    void deleteBeforeDate(String date);

    /**
     * cpu 监控历史数据
     *
     * @param params
     * @return
     */
    Result pageCpu(Map<String, Object> params);

    /**
     * 内存 监控历史数据
     *
     * @param params
     * @return
     */
    Result pageMemory(Map<String, Object> params);
}