package com.szzt.iot.agent.activemq.consumer.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.szzt.iot.agent.activemq.consumer.IMessageConsumerService;
import com.szzt.iot.agent.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.activemq.ActivemqMsg;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import com.szzt.iot.common.activemq.sikulix.WorkResultMsgBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 心跳
 *
 * @author zhouhongjin
 */
@Slf4j
//@Component
public class AgentHeartbeatConsumerServiceImpl implements IMessageConsumerService {
    @Resource(name = "agentHeartbeatProducerServiceImpl")
    private IMessageProducerService iMessageProducerService;

    @JmsListener(destination = "robot.to.client.heartbeat.topic")
    @Override
    public void receiveMessage(String message) {
        //消费心跳消息，并且反馈消息给admin
        System.out.println("接收到服务端发送过来的activemq的消息是：" + message);
        String localIp = null;
        try {
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONUtil.parseObj(message);
        MsgHeader msgHeader = jsonObject.get("msgHeader", MsgHeader.class);
        //判断是否为给本机发送的消息
        if (!localIp.equals(msgHeader.getToIp())) {
            return;
        }
        if (msgHeader.getServiceId() == MsgHeaderEnum.ServiceIdEnum.HEARTBEAT_SERVICE.getCode() && msgHeader.getCmdId() == MsgHeaderEnum.HeartbeatMsgCmdIdEnum.TO_CLIENT_HEARTBEAT.getCode()) {
            this.feedbackHeartbeat();
        }
    }

    /**
     * 发送心跳反馈消息
     */
    private void feedbackHeartbeat() {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        try {
            //获取本机ip地址
            msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.HEARTBEAT_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.HeartbeatMsgCmdIdEnum.TO_SERVER_HEARTBEAT.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());
        ActivemqMsg<WorkResultMsgBody> activemqMsg = new ActivemqMsg<>();
        activemqMsg.setMsgHeader(msgHeader);
        iMessageProducerService.sendMessage(activemqMsg);
    }
}
