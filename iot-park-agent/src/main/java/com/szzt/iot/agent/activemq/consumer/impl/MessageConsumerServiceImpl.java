package com.szzt.iot.agent.activemq.consumer.impl;

import com.szzt.iot.agent.activemq.consumer.IMessageConsumerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Component
public class MessageConsumerServiceImpl implements IMessageConsumerService {


    //    @JmsListener(destination = "robot.to.client.script.python.topic")
    @Override
    public void receiveMessage(String message) {

        log.info("接收到服务端发送过来的activemq的消息是：{}", message);

    }

}
