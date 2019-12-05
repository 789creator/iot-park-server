package com.szzt.iot.transfer.listener;

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
    public void process(Map testMessage) {
        System.out.println("TopicSmokeAlarmReceiver  : " + testMessage.toString());
    }
}
