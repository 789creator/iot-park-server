package com.szzt.iot.common.rabbitmq.sikulix;

import com.szzt.iot.common.rabbitmq.RabbitmqMsg;
import com.szzt.iot.common.rabbitmq.MsgHeader;
import com.szzt.iot.common.rabbitmq.MsgHeaderEnum;
import lombok.AllArgsConstructor;

/**
 * 消息构建
 *
 * @author zhouhongjin
 */
@AllArgsConstructor
public class SikulixMsgBuilder<T> {

    T body;

    public RabbitmqMsg<T> builder(SikulixMsgCmdEnum sikulixMsgCmdEnum) {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SIKULIX_SERVICE.getCode());
        msgHeader.setCmdId(sikulixMsgCmdEnum.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());

        RabbitmqMsg<T> rabbitmqMsg = new RabbitmqMsg<>();
        rabbitmqMsg.setMsgHeader(msgHeader);
        rabbitmqMsg.setMsgBody(body);
        return rabbitmqMsg;
    }
}
