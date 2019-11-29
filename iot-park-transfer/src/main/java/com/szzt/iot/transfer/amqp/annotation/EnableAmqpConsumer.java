package com.szzt.iot.transfer.amqp.annotation;

import com.szzt.iot.transfer.amqp.config.AmqpConsumerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * amqp 消息订阅启动注解
 * @author zhouhongjin
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(AmqpConsumerConfig.class)
public @interface EnableAmqpConsumer {

}
