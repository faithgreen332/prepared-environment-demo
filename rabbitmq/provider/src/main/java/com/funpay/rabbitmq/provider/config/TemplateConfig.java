package com.funpay.rabbitmq.provider.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
@Component
public class TemplateConfig {

    @Autowired
    FunPayConfirmCallback confirmCallback;
    @Autowired
    FunPayReturnCallback returnCallback;

    @Autowired
    @Qualifier("cachingConnFactory")
    ConnectionFactory factory;

    @Bean
    public RabbitTemplate funPayRabbitTemplate() {
        RabbitTemplate t = new RabbitTemplate();
        t.setConnectionFactory(factory);
        // 手动确认
        t.setMandatory(true);
        t.setConfirmCallback(confirmCallback);
        t.setReturnCallback(returnCallback);

        t.setMessageConverter(new Jackson2JsonMessageConverter());
        return t;
    }
}
