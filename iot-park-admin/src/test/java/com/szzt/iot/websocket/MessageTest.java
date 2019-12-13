package com.szzt.iot.websocket;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.szzt.iot.common.websocket.MessageHeader;
import com.szzt.iot.common.websocket.WebSocketMessage;
import com.szzt.iot.common.websocket.body.SmokeAlarmWebsocketBody;
import org.junit.Test;

public class MessageTest {

    @Test
    public void getMessageJsonStr(){
        MessageHeader header = new MessageHeader();
        header.setActionCode(0);
        header.setSendTime(DateUtil.now());
        SmokeAlarmWebsocketBody body = new SmokeAlarmWebsocketBody();
        body.setBatteryVoltage(10.0);
        body.setDeviceName("test");
        body.setDeviceType(1);
        body.setSmokeStatus("0");

        WebSocketMessage<SmokeAlarmWebsocketBody> message = new WebSocketMessage<>();
        message.setMessageHeader(header);
        message.setMessageBody(body);

        System.out.println(JSONUtil.toJsonStr(message));
    }
}
