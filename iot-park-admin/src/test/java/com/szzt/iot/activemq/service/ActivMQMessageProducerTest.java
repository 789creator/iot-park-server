package com.szzt.iot.activemq.service;

import cn.hutool.json.JSONUtil;
import com.szzt.iot.common.activemq.ActivemqMsg;
import com.szzt.iot.common.activemq.JudgeReplyResultMsgBody;
import com.szzt.iot.common.activemq.MsgHeader;
import com.szzt.iot.common.activemq.MsgHeaderEnum;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * activemq 消息队列生产者
 *
 * @author zhouhongjin
 */
public class ActivMQMessageProducerTest {

    private static ActivMQMessageProducerTest activMQMessageProducer;

    private MessageProducer producer;

    private Session session;

    private Connection connection;
    /**
     * 消息队列名称
     */
    private String queueName;
    private String topicName;
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

    private ActivMQMessageProducerTest() throws JMSException {
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
    public static ActivMQMessageProducerTest getInstance() throws JMSException {
        if (activMQMessageProducer == null) {
            synchronized (ActivMQMessageProducerTest.class) {
                if (activMQMessageProducer == null) {
                    activMQMessageProducer = new ActivMQMessageProducerTest();
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
        this.connection = activeMQConnectionFactory.createConnection();
        // 打开连接
        this.connection.start();

    }

    private void subQueue() throws JMSException {
        //创建会话
        session = this.connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createQueue(queueName);
        //创建一个生产者
        producer = session.createProducer(destination);
    }

    private void subTopic() throws JMSException {
        //创建会话
        session = this.connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createTopic(topicName);
        //创建一个生产者
        producer = session.createProducer(destination);
    }

    public void sendMessage(String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage(message);
        producer.send(textMessage);
        System.out.println("客户端发送的消息是：" + textMessage.getText());
        session.commit();
    }

    public static void main(String[] args) throws JMSException {
        //创建消息头
        MsgHeader msgHeader = new MsgHeader();
        try {
            //获取本机ip地址
            msgHeader.setFromIp(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        msgHeader.setServiceId(MsgHeaderEnum.ServiceIdEnum.JUDGE_SERVICE.getCode());
        msgHeader.setCmdId(MsgHeaderEnum.JudgeMsgCmdIdEnum.TO_CLIENT_JUDGE_REPLY.getCode());
        msgHeader.setSendTime(System.currentTimeMillis());
        JudgeReplyResultMsgBody judgeReplyResultMsgBody = new JudgeReplyResultMsgBody();
        judgeReplyResultMsgBody.setProjectNO("123243");
        judgeReplyResultMsgBody.setIsBidEvaluation(1);
        judgeReplyResultMsgBody.setProjectName("测试");
        judgeReplyResultMsgBody.setJudgePhone("18569080390");
        //创建消息体
        ActivemqMsg<JudgeReplyResultMsgBody> activemqMsg = new ActivemqMsg<>();
        activemqMsg.setMsgHeader(msgHeader);
        activemqMsg.setMsgBody(judgeReplyResultMsgBody);
        ActivMQMessageProducerTest activMQMessageProducerTest = new ActivMQMessageProducerTest();
        activMQMessageProducerTest.topicName = "robot.to.judge.reply.result.topic";
        activMQMessageProducerTest.subTopic();
        activMQMessageProducerTest.sendMessage(JSONUtil.toJsonStr(activemqMsg));
    }
}
