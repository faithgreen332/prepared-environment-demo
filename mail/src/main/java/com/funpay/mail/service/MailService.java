package com.funpay.mail.service;

import com.funpay.mail.config.DefaultMailConfig;
import com.funpay.mail.vo.MailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
@Component
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    DefaultMailConfig defaultMailConfig;

    public void sendSimpleMail(MailEntity entity) {
        SimpleMailMessage message = new SimpleMailMessage();

        String a;
        message.setSubject((a = entity.getSubject()) == null ? defaultMailConfig.getDefaultSubject() : a);
        message.setText((a = entity.getText()) == null ? defaultMailConfig.getDefaultText() : a);
        message.setFrom((a = entity.getFrom()) == null ? defaultMailConfig.getDefaultFrom() : a);
        message.setTo((a = entity.getTo()) == null ? defaultMailConfig.getDefaultTo() : a);
        message.setSentDate(new Date());

        mailSender.send(message);
    }

}
