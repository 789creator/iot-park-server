package com.szzt.iot.transfer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhongjin
 */
@Configuration
public class TopicSmokeAlarmRabbitConfig {

    public final static String SMOKE_ALARM_TOPIC = "topic.device.smoke.alarm.transfer";
    public final static String SMOKE_ALARM_TOPIC_ROUTE_KEY = "topic.device.smoke.#";

    @Bean
    public Queue smokeAlarmQueue() {
        return new Queue(SMOKE_ALARM_TOPIC);
    }

    @Bean
    public TopicExchange smokeAlarmTopicExchange() {
        return new TopicExchange("smokeAlarmTopicExchange");
    }

    @Bean
    public Binding bindingSmokeAlarmExchangeMessage() {
        return BindingBuilder.bind(smokeAlarmQueue()).to(smokeAlarmTopicExchange()).with(SMOKE_ALARM_TOPIC_ROUTE_KEY);
    }

}
