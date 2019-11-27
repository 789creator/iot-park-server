package com.szzt.iot.admin.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.szzt.iot.admin.modules.sys.dto.ServerStatusDTO;
import com.szzt.iot.admin.modules.sys.dto.ServersDTO;
import com.szzt.iot.admin.modules.sys.entity.AgentServerEntity;
import com.szzt.iot.common.service.BaseService;

import java.util.List;
import java.util.Map;

public interface AgentServerService extends BaseService<AgentServerEntity> {
    IPage<AgentServerEntity> list(Map<String, Object> params);

    ServersDTO get(Long id);

    void save(ServersDTO dto);

    void update(ServersDTO dto);

    void delete(Long id);

    List<AgentServerEntity> selectFreeServer();

    /**
     * 更新agent在线状态
     *
     * @param agentIp
     * @param isOnline
     */
    void updateServerStatus(String agentIp, int isOnline);

    /**
     * 获取所有的agent 服务器
     */
    List<AgentServerEntity> getAllAgentServer();

    /**
     * 更新agent 占用状态
     * @param serverIp
     * @param status
     */
    void updateServer(String serverIp, int status);

    /**
     * 获取服务器状态及数量
     * @return
     */
    List<ServerStatusDTO> getServerStatus();
}
