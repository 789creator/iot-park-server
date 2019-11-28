package com.szzt.iot.admin.modules.activemq.consumer.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.consumer.IMessageConsumerService;
import com.szzt.iot.admin.modules.sys.service.AgentServerService;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class HeartbeatConsumerServiceImpl implements IMessageConsumerService {

    @Autowired
    private AgentServerService agentServerService;

    @JmsListener(destination = "robot.to.server.heartbeat.topic")
    @Override
    public void receiveMessage(String message) {
        System.out.println("接收到客户端发过来的心跳消息：" + message);
        JSONObject jsonObject = JSONUtil.parseObj(message);
        MsgHeader msgHeader = jsonObject.get("msgHeader", MsgHeader.class);
        if (msgHeader.getServiceId() != MsgHeaderEnum.ServiceIdEnum.HEARTBEAT_SERVICE.getCode()) {
            return;
        }
        int cmdId = msgHeader.getCmdId();
        if (MsgHeaderEnum.HeartbeatMsgCmdIdEnum.TO_SERVER_HEARTBEAT.getCode() == cmdId) {
            //更新agent服务器状态
            agentServerService.updateServerStatus(msgHeader.getFromIp(), 1);
        }

    }
}
