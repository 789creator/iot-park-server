package com.szzt.iot.transfer.test.amqp.service;

import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.smokealarm.SmokeAlarmMsgBody;
import com.szzt.iot.transfer.config.TopicSmokeAlarmRabbitConfig;
import com.szzt.iot.transfer.db.entity.DeviceSmokeAlarmEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class SmokeMessage {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到消息队列
     * <p>
     * //     * @param deviceStatus
     */
    public void testMessage(DeviceSmokeAlarmEntity deviceSmokeAlarmEntity) throws UnknownHostException {

        // 消息头
        MsgHeader msgHeader = new MsgHeader();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        msgHeader.setSendTime(time);
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SMOKE_ALARM_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.SmokeAlarmMsgCmdIdEnum.SMOKE_ALARM_FIRE.getCode());
        msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());

        // 消息体
        SmokeAlarmMsgBody smokeAlarmMsgBody = new SmokeAlarmMsgBody();
        smokeAlarmMsgBody.setDeviceStatus(deviceSmokeAlarmEntity.getDeviceStatus());
        smokeAlarmMsgBody.setDeviceName(deviceSmokeAlarmEntity.getDeviceName());
        smokeAlarmMsgBody.setBatteryVoltage(deviceSmokeAlarmEntity.getBatteryVoltage());
        smokeAlarmMsgBody.setDeviceType(deviceSmokeAlarmEntity.getDeviceType());
        switch (deviceSmokeAlarmEntity.getDeviceStatus()){
            case 0:
                smokeAlarmMsgBody.setSmokeStatus("正常状态");
                break;
            case 1:
                smokeAlarmMsgBody.setSmokeStatus("火警");
                break;
            case 2:
                smokeAlarmMsgBody.setSmokeStatus("传感器故障");
                break;
            case 3:
                smokeAlarmMsgBody.setSmokeStatus("电池低电压");
                break;
            case 4:
                smokeAlarmMsgBody.setSmokeStatus("火警解除");
                break;
            case 5:
                smokeAlarmMsgBody.setSmokeStatus("传感器故障解除");
                break;
            case 6:
                smokeAlarmMsgBody.setSmokeStatus("电池低电压解除");
                break;
            case 13:
                smokeAlarmMsgBody.setSmokeStatus("设备自检");
                break;
            case 14:
                smokeAlarmMsgBody.setSmokeStatus("设备上电");
                break;
        }
        RabbitmqMsg rabbitmqMsg = new RabbitmqMsg<SmokeAlarmMsgBody>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody(smokeAlarmMsgBody);
        rabbitTemplate.convertAndSend("smokeAlarmTopicExchange", TopicSmokeAlarmRabbitConfig.SMOKE_ALARM_TOPIC, rabbitmqMsg);

    }
}
