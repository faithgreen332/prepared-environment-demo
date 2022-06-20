package com.funpay.rabbitmq.consumer.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static com.fanpay.rabbitmq.BindingConfigConstant.*;

/**
 * 这种写法不会写配置 consumer 的并发，换一种
 *
 * @author Leeko
 * @date 2022/3/2
 **/
@Configuration
@Slf4j
public class RabbitmqListener {

    /**
     * 这种设置 messageListener 的方法只能全局配置，比如监听某个 queue 的并发量，每次消费者一批取的条数等，一旦设置了，所有的 messageListener 都是一样的配置，
     * 这样显然是不合理的，应该根据业务需要自定义对特定 queue 的逻辑，那么应该使用 DirectMessageListenerContainer（2.0之前的版本默认的是 SimpleMessageListenerContainer,
     * 这个 simple 容器并发量高的情况下并发的 consumer 会共享使用 channel，而 channel 之间是线程隔离的，所以会有线程上下文切换的消耗影响性能，所以 rabbitmq 官方再2.0之后推出了 Direct 的这种，
     * 这个 Direct 是一个 channel 一个 consumer 线程，没有了上下文切换的消耗，应该性能会好一点） 的方式。
     * 这个是监听死信队列的，搞一个就够了
     *
     * @param channel
     * @param message
     */
    @RabbitListener(queues = REMIT_IN_DEATH_QUEUE)
    public void deathRemitInQueueHandler(Channel channel, Message message) {
        System.out.println("消费私信队列：channel:" + channel.getChannelNumber() + ",thread:" + Thread.currentThread().getName()
                + ",consumerTag:" + message.getMessageProperties().getConsumerTag() +
                ",message:" + new String(message.getBody()));
    }

    /**
     * 监听 remit_out 的死信队列的监听器，搞一个就够了
     *
     * @param channel
     * @param message
     */
    @RabbitListener(queues = REMIT_OUT_DEATH_QUEUE)
    public void deathRemitOutQueueHandler(Channel channel, Message message) {
        System.out.println("消费私信队列：channel:" + channel.getChannelNumber() + ",thread:" + Thread.currentThread().getName()
                + ",consumerTag:" + message.getMessageProperties().getConsumerTag() +
                ",message:" + new String(message.getBody()));
    }

    @Value("${spring.rabbitmq.remit-out.listener.consumers-per-queue}")
    private int consumersPerQueue;

    @Value("${spring.rabbitmq.remit-out.listener.prefetch}")
    private int prefetch;

    @Autowired
    @Qualifier("funPayConnectionFactory")
    ConnectionFactory connectionFactory;

    /**
     * 监听器容器对象，之所以配置容器对象而不是单独配置监听器，是为了能定制化对单个 queue 的消费策略，
     * 这个容器监听了 REMIT_OUT_QUEUE 和 REMIT_IN_QUEUE 队列，每个消费者并发数是2（consumersPerQueue），每批取 1（prefetch） 条消息
     *
     * @return
     */
    @Bean("funPayContainer")
    public DirectMessageListenerContainer directMessageListenerContainer() {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(REMIT_OUT_QUEUE);
        container.addQueueNames(REMIT_IN_QUEUE);
        // 设置consumer 并发
        container.setConsumersPerQueue(consumersPerQueue);
        // 设置手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 每次consumer就取一条，让剩下的去别的consumer消费
        container.setPrefetchCount(prefetch);

        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws IOException {
                MessageProperties prop = message.getMessageProperties();
                byte[] body = message.getBody();
                try {
//                    System.out.println(prop.getConsumerQueue());
//                    System.out.println("thread:" + Thread.currentThread().getName()
//                            + ",channel:" + channel.getChannelNumber()
//                            + ",consumerTag:" + prop.getConsumerTag()
//                            + ",message:" + new String(body));
                    int a = 1 / 0;
                    // 妈的这个 ack 是异步的，怎么保证一定通知到呢？
                    channel.basicAck(prop.getDeliveryTag(), false);
                } catch (Exception e) {
                    log.error("放进死信队列：" + new String(body));
                    channel.basicReject(prop.getDeliveryTag(), false);
                }
            }
        });
        return container;
    }
}
