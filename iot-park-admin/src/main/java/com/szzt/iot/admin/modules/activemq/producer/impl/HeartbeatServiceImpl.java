package com.szzt.iot.admin.modules.activemq.producer.impl;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.activemq.ActivemqMsg;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import com.szzt.iot.common.activemq.sikulix.WorkResultMsgBody;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 发送心跳检测消息给agent
 *
 * @author zhouhongjin
 */
//@Service
public class HeartbeatServiceImpl implements IMessageProducerService {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "heartbeatTopic")
    private Topic topic;

    @Override
    public void sendMessage(Object msg) {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        try {
            //获取本机ip地址
            msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.HEARTBEAT_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.HeartbeatMsgCmdIdEnum.TO_CLIENT_HEARTBEAT.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());
        msgHeader.setToIp((String) msg);
        ActivemqMsg<WorkResultMsgBody> activemqMsg = new ActivemqMsg<>();
        activemqMsg.setMsgHeader(msgHeader);
        this.jmsMessagingTemplate.convertAndSend(this.topic, JSONUtil.toJsonStr(activemqMsg));
    }
}
