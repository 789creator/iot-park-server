package com.szzt.iot.transfer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连型交换机 rabbitmq 配置
 * @author zhouhongjin
 */
@Configuration
public class DirectRabbitmqConfig {

    @Bean
    public Queue testDirectQueue(){
        //true 是否持久
        return new Queue("testDirectQueue",true);
    }
    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    public Binding bindingDirect(@Qualifier("testDirectQueue") Queue queue, @Qualifier("testDirectExchange") DirectExchange directExchange) {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with("testDirectRouting");
    }


}
