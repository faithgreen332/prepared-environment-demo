package com.funpay.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Leeko
 * @date 2022/2/15
 **/
@Configuration
public class RedisClusterConfiguration {

    @Autowired
    private RedisClusterProperties redisProperties;

    @Bean
    public JedisCluster jedisCluster() {
        String[] serverArray = redisProperties.getClusterNodes().split(",");

        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.parseInt(ipPortPair[1].trim())));
        }

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxActive());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(true);

        return new JedisCluster(nodes, redisProperties.getConnectionTimeout(),
                redisProperties.getExpireSeconds(), redisProperties.getMaxAttempts(),
                redisProperties.getPassword().trim(), jedisPoolConfig);
    }
}
