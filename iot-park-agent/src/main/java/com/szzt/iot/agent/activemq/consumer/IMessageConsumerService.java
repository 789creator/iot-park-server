package com.szzt.iot.agent.activemq.consumer;

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
