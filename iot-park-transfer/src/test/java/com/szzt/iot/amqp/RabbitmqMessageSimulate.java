package com.szzt.iot.amqp;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.smokealarm.SmokeAlarmMsgBody;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq消息模拟发送
 */
public class RabbitmqMessageSimulate {
    private String QUEUE_NAME = "topic.device.smoke.alarm.api";
    private Connection connection;
    private Channel channel;

    @Before
    public void getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("172.20.31.41");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
//        factory.setVirtualHost("testhost");
        factory.setUsername("iot-park");
        factory.setPassword("iot-park");
        // 通过工程获取连接
        this.connection = factory.newConnection();
    }

    @After
    public void closeConnection() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }

    @Test
    public void sendMessage() throws IOException {
        this.channel = this.connection.createChannel();
        channel.queueDeclare();
        // 消息内容

        // 消息头
        MsgHeader msgHeader = new MsgHeader();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        msgHeader.setSendTime(time);
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SMOKE_ALARM_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.SmokeAlarmMsgCmdIdEnum.SMOKE_ALARM_FIRE.getCode());
        msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
        // 消息体
        SmokeAlarmMsgBody smokeAlarmMsgBody = new SmokeAlarmMsgBody();
        smokeAlarmMsgBody.setDeviceStatus(1);
        smokeAlarmMsgBody.setDeviceName("d896e0001c000543");
        smokeAlarmMsgBody.setBatteryVoltage(Double.valueOf(10));
        smokeAlarmMsgBody.setDeviceType(2);
        RabbitmqMsg rabbitmqMsg = new RabbitmqMsg<SmokeAlarmMsgBody>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody(smokeAlarmMsgBody);

        channel.basicPublish("", QUEUE_NAME, null, JSONUtil.toJsonStr(rabbitmqMsg).getBytes());
        System.out.println("Send '" + JSONUtil.toJsonStr(rabbitmqMsg) + "'");
    }

}