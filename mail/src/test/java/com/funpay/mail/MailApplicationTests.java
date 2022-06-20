package com.funpay.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

@SpringBootTest
class MailApplicationTests {

    @Autowired
    JavaMailSender mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是一封测试邮件");
        message.setFrom("lijinfeigreen@vip.163.com");
        // lijinfeigreen@vip.163.com leeko@funmobi.asia
        message.setTo("lijinfeigreen@vip.163.com");
//        message.setCc("37xxxxx37@qq.com");
//        message.setBcc("14xxxxx098@qq.com");
        message.setSentDate(new Date());
        message.setText("这是测试邮件的正文");
        mailSender.send(message);
    }

}
