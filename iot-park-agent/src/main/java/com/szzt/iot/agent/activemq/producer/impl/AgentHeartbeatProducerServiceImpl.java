package com.szzt.iot.agent.activemq.producer.impl;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.agent.activemq.producer.IMessageProducerService;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Topic;

/**
 * 回复心跳
 *
 * @author zhouhongjin
 */
//@Service
@Slf4j
public class AgentHeartbeatProducerServiceImpl implements IMessageProducerService {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "heartbeatTopic")
    private Topic topic;

    @Override
    public void sendMessage(RabbitmqMsg msg) {
        this.jmsMessagingTemplate.convertAndSend(this.topic, JSONUtil.toJsonStr(msg));
    }
}
