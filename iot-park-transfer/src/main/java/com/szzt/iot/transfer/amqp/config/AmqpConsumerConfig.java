package com.szzt.iot.transfer.amqp.config;

import com.szzt.iot.transfer.amqp.SmokeAlarmAmqpConsumer;
import org.springframework.context.annotation.Bean;

/**
 * @author zhouhongjin
 */
public class AmqpConsumerConfig {

    @Bean
    public SmokeAlarmAmqpConsumer smokeAlarmAmqpConsumer() throws Exception {
        SmokeAlarmAmqpConsumer smokeAlarmAmqpConsumer = new SmokeAlarmAmqpConsumer();
        smokeAlarmAmqpConsumer.startConsumer();
        return smokeAlarmAmqpConsumer;
    }
}
