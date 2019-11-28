package com.szzt.iot.admin.common.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * activemq 配置类
 *
 * @author zhouhongjin
 */
//@Configuration
//@EnableJms
public class ActiveMQConfig {

//    @Bean(name = "judgeReplyResultQueue")
//    public Queue queue() {
//        return new ActiveMQQueue("robot.to.judge.reply.result.queue");
//    }

    @Bean(name = "judgeReplyResultTopic")
    public Topic judgeReplyResultTopic() {
        return new ActiveMQTopic("robot.to.judge.reply.result.topic");
    }

    @Bean(name = "scriptTopic")
    public Topic topic() {
        return new ActiveMQTopic("robot.to.client.script.python.topic");
    }

    @Bean(name = "heartbeatTopic")
    public Topic heartbeatTopic() {
        return new ActiveMQTopic("robot.to.client.heartbeat.topic");
    }

    /**
     * JmsListener在配置文件配置了只接收topic消息,如果要接收queue消息,需要设置containerFactory
     */
    @Bean
    public JmsListenerContainerFactory<?> queueListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory queueListenerContainer = new DefaultJmsListenerContainerFactory();
        queueListenerContainer.setPubSubDomain(false);
//        queueListenerContainer.setMessageConverter(new SmsMessageConverter());
        queueListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        return queueListenerContainer;
    }

}