package com.szzt.iot.admin.modules.amqp.listener;

import org.apache.qpid.jms.JmsConnectionListener;
import org.apache.qpid.jms.message.JmsInboundMessageDispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import java.net.URI;

/**
 * SmokeAlarm 连接amqp 监听
 *
 * @author zhouhongjin
 */
public class SmokeAlarmConnectionListener implements JmsConnectionListener {

    private final static Logger logger = LoggerFactory.getLogger(SmokeAlarmConnectionListener.class);

    /**
     * 连接成功建立。
     */
    @Override
    public void onConnectionEstablished(URI remoteURI) {
        logger.info("onConnectionEstablished, remoteUri:{}", remoteURI);
    }

    /**
     * 尝试过最大重试次数之后，最终连接失败。
     */
    @Override
    public void onConnectionFailure(Throwable error) {
        logger.error("onConnectionFailure, {}", error.getMessage());
    }

    /**
     * 连接中断。
     */
    @Override
    public void onConnectionInterrupted(URI remoteURI) {
        logger.info("onConnectionInterrupted, remoteUri:{}", remoteURI);
    }

    /**
     * 连接中断后又自动重连上。
     */
    @Override
    public void onConnectionRestored(URI remoteURI) {
        logger.info("onConnectionRestored, remoteUri:{}", remoteURI);
    }

    @Override
    public void onInboundMessage(JmsInboundMessageDispatch envelope) {
    }

    @Override
    public void onSessionClosed(Session session, Throwable cause) {
    }

    @Override
    public void onConsumerClosed(MessageConsumer consumer, Throwable cause) {
    }

    @Override
    public void onProducerClosed(MessageProducer producer, Throwable cause) {
    }
}
