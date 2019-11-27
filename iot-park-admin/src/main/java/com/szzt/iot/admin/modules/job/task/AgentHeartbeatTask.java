/**
 * Copyright (c) 2019 证通电子 All rights reserved.
 * <p>
 * https://www.szzt.com.cn
 * <p>
 * 版权所有，侵权必究！
 */

package com.szzt.iot.admin.modules.job.task;

import com.szzt.iot.admin.modules.activemq.producer.IMessageProducerService;
import com.szzt.iot.admin.modules.sys.entity.AgentServerEntity;
import com.szzt.iot.admin.modules.sys.service.AgentServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * 检查客户端是否在线
 *
 * @author
 */
//@Component("agentHeartbeatTask")
@Slf4j
public class AgentHeartbeatTask implements ITask {

    @Resource(name = "heartbeatServiceImpl")
    private IMessageProducerService iMessageProducerService;
    @Autowired
    private AgentServerService agentServerService;

    @Override
    public void run(String params) {
        try {
            List<AgentServerEntity> allAgentServer = agentServerService.getAllAgentServer();
            for (AgentServerEntity agentServerEntity : allAgentServer) {
                //修改所有agent为不在线状态
                agentServerService.updateServerStatus(agentServerEntity.getServerIp(),0);
                //发送心跳检测消息
                iMessageProducerService.sendMessage(agentServerEntity.getServerIp());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}