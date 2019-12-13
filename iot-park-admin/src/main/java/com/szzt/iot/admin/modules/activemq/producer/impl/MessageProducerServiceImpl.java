package com.szzt.iot.admin.modules.activemq.producer.impl;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.admin.modules.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
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



    }
}
