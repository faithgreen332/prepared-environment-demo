package com.fanpay.rabbitmq;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息体容器
 * 由于使用手动确认消息，如果消息没有正确发送，那需要把没有正确投递的消息记录下来手工补偿，那需要的信息包括：
 * 1.在 channel 里传输的 correlationId，confirm 方法里能取到
 * 2.对应的消息体的数据，在 ConfirmCallback 阶段在 channel 里取不到，所以只能在 provider 发送之前预存，然后在使用的时候取
 * 3.这样的话，消息体是五花八门的，要反序列化成正确的对象，需要在预存的时候指定 Type，然后能正确反序列化
 * 讲道理，如果真的没有正确投递，要么是服务或者网络问题，要么就是 exchange 名字给错了，
 * 代码是没法自动补偿的，所以只能手动补偿，
 *
 * @author Leeko
 * @date 2022/3/3
 **/
public class DataSource {

    /**
     * 存储 correlationId 和具体消息的对应关系
     */
    public static final ConcurrentHashMap<String, DataWrapper> MAPPING = new ConcurrentHashMap<>();

    public static void addIfAbsent(String id, DataWrapper data) {
        MAPPING.putIfAbsent(id, data);
    }

    public static void remove(String id) {
        MAPPING.remove(id);
    }

    /**
     * 消息体和需要序列化的 Type 类型
     */
    public static class DataWrapper<T> {

        /**
         * 这个是 Class.class，但是需要的是 XXOO.class,这个用 spring 的 Resolvable 应该可以解决
         * 先预留，后续确实需要再说
         */
        private Class<?> type;
        private T entity;

        public Class<?> getType() {
            return type;
        }

        public void setType(Class<?> type) {
            this.type = type;
        }

        public T getEntity() {
            return entity;
        }

        public void setEntity(T entity) {
            this.entity = entity;
        }
    }
}
