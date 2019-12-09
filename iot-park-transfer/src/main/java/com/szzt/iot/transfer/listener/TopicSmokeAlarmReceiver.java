package com.szzt.iot.transfer.listener;

import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.transfer.config.TopicSmokeAlarmRabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhouhongjin
 */
@Component
@RabbitListener(queues = TopicSmokeAlarmRabbitConfig.SMOKE_ALARM_TOPIC)
public class TopicSmokeAlarmReceiver {
    @RabbitHandler
    public void process(RabbitmqMsg testMessage) {
        System.out.println("MsgHeader  : " + testMessage.getMsgHeader());
        System.out.println("MsgBody  : " + testMessage.getMsgBody());
    }
}
