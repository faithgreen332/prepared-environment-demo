package com.funpay.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

@Slf4j
@Component
public class FunRedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private RedisClusterProperties redisProperties;

    /*
     * 获取对象
     */
    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    /**
     * 设置缓存
     *
     * @param key   缓存key
     * @param value 缓存value
     */
    public void set(String key, String value) {
        jedisCluster.set(key, value);
        log.debug("RedisUtil:set cache key={},value={}", key, value);
    }

    /**
     * 设置缓存，并且自己指定过期时间 ex(秒) px(毫秒)
     *
     * @param key
     * @param value
     * @param expireTime 过期时间
     */
    public void setWithExpireTime(String key, int expireTime, String value) {
        jedisCluster.setex(key, expireTime, value);
        log.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, expireTime);
    }


    /**
     * setNx
     *
     * @param key   键
     * @param value 值
     */
    public boolean setIfNotExists(final String key, final String value) {
        log.debug("RedisUtil:setNx cache key={},value={}", key, value);
        return jedisCluster.setnx(key, value) == 1L;
    }

    /**
     * setNx with expireTime
     *
     * @param key        键
     * @param value      值
     * @param expireTime 过期时间，seconds
     */

    public boolean setIfNotExists(final String key, final String value, final long expireTime) {
        if (setIfNotExists(key, value)) {
            log.debug("RedisUtil:setNx cache key={},value={},expireTime={}", key, value, expireTime);
            return jedisCluster.expire(key, (int) expireTime) == 1L;
        } else {
            return false;
        }
    }


    /**
     * 设置缓存，并且由配置文件指定过期时间
     *
     * @param key
     * @param value
     */
    public void setWithExpireTime(String key, String value) {
        int EXPIRE_SECONDS = redisProperties.getExpireSeconds();
        jedisCluster.setex(key, EXPIRE_SECONDS, value);
        log.debug("RedisUtil:setWithExpireTime cache key={},value={},expireTime={}", key, value, EXPIRE_SECONDS);
    }

    /**
     * 获取指定key的缓存
     *
     * @param key
     */
    public String get(String key) {
        String value = jedisCluster.get(key);
        log.debug("RedisUtil:get cache key={},value={}", key, value);
        return value;
    }

    public void delete(String key) {
        jedisCluster.del(key);
        log.debug("RedisUtil:delete cache key={}", key);
    }

    /**
     * 获取key的过期时间
     *
     * @param key
     * @return
     */
    public Long getttl(String key) {
        Long value = jedisCluster.ttl(key);
        log.debug("RedisUtil:getttl cache key={},value={}", key, value);
        return value;
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean exist(String key) {
        return jedisCluster.exists(key);
    }

    /**
     * 获取自增长值
     *
     * @param key
     * @return
     */
    public Long increment(String key) {
        return jedisCluster.incr(key);
    }

    /**
     * 获取自减长值
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

}
