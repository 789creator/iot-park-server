package com.szzt.iot.api.listener;

import com.szzt.iot.api.config.TopicSmokeAlarmRabbitConfig;
import org.springframework.amqp.core.Message;
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
    public void process(Object object) {
        Message message = (Message) object;
        String body = new String(message.getBody());
        System.out.println("TopicSmokeAlarmReceiver  : " + body);
    }
}
