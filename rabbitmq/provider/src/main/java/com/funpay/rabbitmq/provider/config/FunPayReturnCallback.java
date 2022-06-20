package com.funpay.rabbitmq.provider.config;

import com.funpay.mail.service.MailService;
import com.funpay.mail.vo.MailEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
@Configuration
@Slf4j
public class FunPayReturnCallback implements RabbitTemplate.ReturnCallback {

    @Autowired
    MailService mailService;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String s = "replyCode:" + replyCode +
                "\nreplyText:" + replyText +
                "\nexchange:" + exchange +
                "\nroutingKey:" + routingKey +
                "\nmessageBody:" + new String(message.getBody());
        mailService.sendSimpleMail(constructMailEntity(s));
        log.error("confirmCallback error：" + s);
    }

    private MailEntity constructMailEntity(String... cause) {
        MailEntity entity = new MailEntity();
        entity.setSubject("发送到rabbitmq出错");
        entity.setText("推送消息到rabbitmq时，在进入 queue 时出错，原因是： " + Arrays.toString(cause));
        return entity;
    }
}
