package com.funpay.rabbitmq.provider.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.fanpay.rabbitmq.BindingConfigConstant.*;

/**
 * remit in 的 exchange、queue 的匹配设置
 *
 * @author Leeko
 * @date 2022/3/2
 **/
@Configuration
public class RemitInBindingConfig {

    @Bean
    public DirectExchange directRemitInExchange() {
        return new DirectExchange(REMIT_IN_EXCHANGE, true, false);
    }


    @Bean
    public Queue remitInQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", DEATH_IN_EXCHANGE);
        args.put("x-dead-letter-routing-key", REMIT_IN_DEATH_ROUTING_KEY);
        return new Queue(REMIT_IN_QUEUE, true, false, false, args);
    }

    @Bean
    public Binding remitInBinding() {
        return BindingBuilder.bind(remitInQueue()).to(directRemitInExchange()).with(REMIT_IN_ROUTING_KEY);
    }

    @Bean
    public DirectExchange directDeathInExchange() {
        return new DirectExchange(DEATH_IN_EXCHANGE, true, false);
    }

    @Bean
    public Queue deathInQueue() {
        return new Queue(REMIT_IN_DEATH_QUEUE, true, false, false);
    }

    @Bean
    public Binding deathInBinding() {
        return BindingBuilder.bind(deathInQueue()).to(directDeathInExchange()).with(REMIT_IN_DEATH_ROUTING_KEY);
    }
}
