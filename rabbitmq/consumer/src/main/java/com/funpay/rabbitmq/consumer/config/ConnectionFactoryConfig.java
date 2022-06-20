package com.funpay.rabbitmq.consumer.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 看文档如果不配置 DirectMessageListenerContainer 的话，会用 SimpleMessageListenerContainer，
 * 而后者的 channel 和 consumer 是
 *
 * @author Leeko
 * @date 2022/3/2
 **/
@Configuration
public class ConnectionFactoryConfig {
    @Value("${spring.rabbitmq.host}")
    public String host;
    @Value("${spring.rabbitmq.port}")
    public int port;

    @Value("${spring.rabbitmq.username}")
    public String username;

    @Value("${spring.rabbitmq.password}")
    public String password;

    @Value("${spring.rabbitmq.virtual-host}")
    public String virtualHost;

    @Bean("funPayConnectionFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPort(port);
        return cachingConnectionFactory;
    }
}
