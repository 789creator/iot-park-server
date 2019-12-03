package com.szzt.iot.transfer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhouhongjin
 */
@Component
@RabbitListener(queues = "testDirectQueue")
public class DirectRabbitmqTestListener {

    @RabbitHandler
    public void handler(Map testMessage) {
        System.out.println("DirectRabbitmqTestListener消费者收到消息  : " + testMessage.toString());
    }
}
