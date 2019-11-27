package com.szzt.iot.admin.modules.activemq.consumer.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.consumer.IMessageConsumerService;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorHistoryService;
import com.szzt.iot.admin.modules.agent.service.AgentSystemMonitorService;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import com.szzt.iot.common.activemq.system.SystemMonitorBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * agent 系统监控 消费者
 */
@Slf4j
@Component
public class SystemMonitorConsumerServiceImpl implements IMessageConsumerService {


    @Resource
    private AgentSystemMonitorHistoryService agentSystemMonitorHistoryService;
    @Resource
    private AgentSystemMonitorService agentSystemMonitorService;


    @JmsListener(destination = "robot.to.server.system.monitor.queue", containerFactory = "queueListenerContainer")
    @Override
    public void receiveMessage(String message) {

        //写到数据库里面
        JSONObject jsonObject = JSONUtil.parseObj(message);
        MsgHeader msgHeader = jsonObject.get("msgHeader", MsgHeader.class);
        if (msgHeader.getServiceId() != MsgHeaderEnum.ServiceIdEnum.SYSTEM_MONITOR_SERVICE.getCode()) {
            return;
        }
        int cmdId = msgHeader.getCmdId();
        if (MsgHeaderEnum.SystemMonitorCmdIdEnum.TO_SERVER_MONITOR_DATA.getCode() == cmdId) {
            SystemMonitorBody body = jsonObject.get("msgBody", SystemMonitorBody.class);
            //更新状态agent服务器的实时状态
            agentSystemMonitorService.updateAgent(body);
            // 写入历史记录表中
            agentSystemMonitorHistoryService.saveSystemMonitor(body);
        }

    }
}
