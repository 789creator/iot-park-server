package com.szzt.iot.admin.modules.activemq.consumer;

/**
 * activemq 消息消费接口
 */
public interface IMessageConsumerService {
    /**
     * 接收到消息
     *
     * @param message
     */
    void receiveMessage(String message);
}
