package com.szzt.iot.admin.modules.amqp.config;

import com.szzt.iot.admin.modules.amqp.SmokeAlarmAmqpConsumer;
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
