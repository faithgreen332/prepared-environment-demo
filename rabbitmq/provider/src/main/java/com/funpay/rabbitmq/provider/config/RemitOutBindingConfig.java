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
 * remit out 的 exchange、queue 的匹配设置
 *
 * @author Leeko
 * @date 2022/3/2
 **/
@Configuration
public class RemitOutBindingConfig {

    @Bean
    public DirectExchange directRemitOutExchange() {
        return new DirectExchange(REMIT_OUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue remitOutQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", DEATH_OUT_EXCHANGE);
        args.put("x-dead-letter-routing-key", REMIT_OUT_DEATH_ROUTING_KEY);
        return new Queue(REMIT_OUT_QUEUE, true, false, false, args);
    }

    @Bean
    public Binding remitOutBinding() {
        return BindingBuilder.bind(remitOutQueue()).to(directRemitOutExchange()).with(REMIT_OUT_ROUTING_KEY);
    }

    @Bean
    public DirectExchange deathOutExchange() {
        return new DirectExchange(DEATH_OUT_EXCHANGE, true, false);
    }

    @Bean
    public Queue deathOutQueue() {
        return new Queue(REMIT_OUT_DEATH_QUEUE, true, false, false);
    }

    @Bean
    public Binding deathOutBinding() {
        return BindingBuilder.bind(deathOutQueue()).to(deathOutExchange()).with(REMIT_OUT_DEATH_ROUTING_KEY);
    }
}
