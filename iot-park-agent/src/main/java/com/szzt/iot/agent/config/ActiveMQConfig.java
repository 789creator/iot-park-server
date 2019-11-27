package com.szzt.iot.agent.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

/**
 * activemq 配置类
 */
//@Configuration
//@EnableJms
public class ActiveMQConfig {

    @Bean(name = "scriptTopic")
    public Topic topic() {
        return new ActiveMQTopic("robot.to.server.script.python.topic");
    }
    @Bean(name = "heartbeatTopic")
    public Topic heartbeatTopic() {
        return new ActiveMQTopic("robot.to.server.heartbeat.topic");
    }

    @Bean(name = "exeResultQueue")
    public Queue exeResultQueue() {
        return new ActiveMQQueue("robot.to.server.exe.result.queue");
    }
}