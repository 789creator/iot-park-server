package com.szzt.iot.common.activemq.sikulix;

import com.szzt.iot.common.activemq.ActivemqMsg;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import lombok.AllArgsConstructor;

/**
 * 消息构建
 *
 * @author zhouhongjin
 */
@AllArgsConstructor
public class SikulixMsgBuilder<T> {

    T body;

    public ActivemqMsg<T> builder(SikulixMsgCmdEnum sikulixMsgCmdEnum) {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.SIKULIX_SERVICE.getCode());
        msgHeader.setCmdId(sikulixMsgCmdEnum.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());

        ActivemqMsg<T> activemqMsg = new ActivemqMsg<>();
        activemqMsg.setMsgHeader(msgHeader);
        activemqMsg.setMsgBody(body);
        return activemqMsg;
    }
}
