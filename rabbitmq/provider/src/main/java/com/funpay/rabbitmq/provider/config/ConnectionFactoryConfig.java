package com.funpay.rabbitmq.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leeko
 * @date 2022/3/2
 **/
@Configuration
@Slf4j
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

    @Bean("cachingConnFactory")
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        // 这行如果设置，会报没有对应的 exchange 的错
//        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        // 如果上一行不开，这两行会报错，所以还是注释掉吧，
//        cachingConnectionFactory.setChannelCacheSize(4);
//        cachingConnectionFactory.setConnectionCacheSize(4);
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        cachingConnectionFactory.setPublisherReturns(true);
        cachingConnectionFactory.setHost(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        cachingConnectionFactory.setVirtualHost(virtualHost);
        cachingConnectionFactory.setPort(port);
        return cachingConnectionFactory;
    }
}
