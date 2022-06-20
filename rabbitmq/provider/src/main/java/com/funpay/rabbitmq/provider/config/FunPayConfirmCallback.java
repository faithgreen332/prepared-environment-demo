package com.funpay.rabbitmq.provider.config;

import com.alibaba.fastjson.JSON;
import com.fanpay.rabbitmq.DataSource;
import com.funpay.mail.service.MailService;
import com.funpay.mail.vo.MailEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


/**
 * 如果消息没有正确投递到 exchange ，可选的处理是日志记录、发邮件通知人工处理、入库然后定时任务处理等
 * 但是业务流程上数据的上游有事务保证了，所以这里就大日志和发邮件告知即可
 *
 * @author Leeko
 * @date 2022/3/3
 **/
@Component
@Slf4j
public class FunPayConfirmCallback implements RabbitTemplate.ConfirmCallback {

    @Autowired
    MailService mailService;

    /**
     * 这里需要 trade off 一下，是尝试放到别的 exchange 然后发送到哪个 queue，然后再监听这个 queue 发邮件还是直接发？
     * 还是直接发吧，因为没有放进去 exchange，在 exchange 没写错的前提下，因该是服务或者网络出问题了，再发到别的 exchange 也发不进去啊
     * 入库也是一样的道理
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            DataSource.DataWrapper dataWrapper = DataSource.MAPPING.get(correlationData.getId());
            mailService.sendSimpleMail(constructMailEntity(correlationData.getId(), cause));
            log.error("confirmCallback error：" + JSON.toJSONString(dataWrapper.getEntity()), dataWrapper.getType());
        }
    }

    private MailEntity constructMailEntity(String... cause) {
        MailEntity entity = new MailEntity();
        entity.setSubject("发送到rabbitmq出错");
        entity.setText("推送消息到rabbitmq时，在进入 exchange 时出错，原因是： " + Arrays.toString(cause));
        return entity;
    }
}
