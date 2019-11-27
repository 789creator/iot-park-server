package com.szzt.iot.agent.bridge;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * activemq 消息队列生产者
 * @author zhouhongjin
 */
public class ActivMQMessageProducer {

    private static ActivMQMessageProducer activMQMessageProducer;

    private MessageProducer producer;

    private Session session;
    /**
     * 消息队列名称
     */
    private String queueName;
    /**
     * 定义ActivMQ的连接地址
     */
    private String brokerURL;
    /**
     * 账号名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    private ActivMQMessageProducer() throws JMSException {
        this.initConnectionProp();
        this.initActiveMQ();
    }

    /**
     * 初始化activemq 需要的信息，读取配置文件
     */
    private void initConnectionProp() {
        this.queueName = PropSettings.setting.get("activemq", "queueName");
        this.brokerURL = PropSettings.setting.get("activemq", "brokerURL");
        this.username = PropSettings.setting.get("activemq", "username");
        this.password = PropSettings.setting.get("activemq", "password");
    }

    /**
     * 实例化对象，采用单例模式
     *
     * @return
     * @throws JMSException
     */
    public static ActivMQMessageProducer getInstance() throws JMSException {
        if (activMQMessageProducer == null) {
            synchronized (ActivMQMessageProducer.class) {
                if (activMQMessageProducer == null) {
                    activMQMessageProducer= new ActivMQMessageProducer();
                    return activMQMessageProducer;
                }
            }
        }
        return activMQMessageProducer;
    }

    /**
     * 初始化连接activemq
     *
     * @throws JMSException
     */
    private void initActiveMQ() throws JMSException {
        // 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        connection.start();
        //创建会话
        session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createQueue(queueName);
        //创建一个生产者
        producer = session.createProducer(destination);
    }

    public void sendMessage(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        session.commit();
    }

}
