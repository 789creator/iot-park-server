package com.szzt.iot.admin.modules.activemq.producer.impl;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import com.szzt.iot.common.rabbitmq.sikulix.SikulixScriptMsgBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
//@Service
public class MessageProducerServiceImpl implements IMessageProducerService {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "scriptTopic")
    private Topic topic;

    @Override
    public void sendMessage(Object msg) {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        try {
            //获取本机ip地址
            msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
            log.debug("本机的ip：{}",InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SIKULIX_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.SikulixMsgCmdIdEnum.TO_CLIENT_EXE_SCRIPT.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());
        msgHeader.setToIp("172.20.31.12");

        //创建消息体
        SikulixScriptMsgBody body = new SikulixScriptMsgBody();
        body.setClientIp("172.20.31.12");
        body.setFeedback(true);
        body.setScriptId(1L);
        body.setScriptPath("Sikulix\\script\\test\\test.sikuli\\test.py");
        body.setScriptName("test");

        RabbitmqMsg<SikulixScriptMsgBody> rabbitmqMsg = new RabbitmqMsg<>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody(body);

        this.jmsMessagingTemplate.convertAndSend(this.topic, JSONUtil.toJsonStr(rabbitmqMsg));

    }
}
