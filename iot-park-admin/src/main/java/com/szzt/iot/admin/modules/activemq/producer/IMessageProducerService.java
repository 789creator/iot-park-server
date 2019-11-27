package com.szzt.iot.admin.modules.activemq.producer;

/**
 * 发送消息 接口类
 */
public interface IMessageProducerService {
    /**
     * 发送消息到activemq
     *
     * @param msg
     */
    public void sendMessage(Object msg);
}
