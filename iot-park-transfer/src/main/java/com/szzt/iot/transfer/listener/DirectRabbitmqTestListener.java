package com.szzt.iot.transfer.listener;

import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;
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
    public void handler(DeviceSmokeAlarmEntity deviceSmokeAlarmEntity) {
        System.out.println("DirectRabbitmqTestListener消费者收到消息  : Smoke设备名称" + deviceSmokeAlarmEntity.getDeviceName());
        System.out.println("DirectRabbitmqTestListener消费者收到消息  : Smoke状态 " + deviceSmokeAlarmEntity.getDeviceStatus());
    }
}
