package com.funpay.rabbitmq.provider.controller;

import com.fanpay.rabbitmq.DataSource;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.fanpay.rabbitmq.BindingConfigConstant.*;

/**
 * @author Leeko
 * @date 2022/3/2
 **/
@RestController
@RequestMapping("producer")
public class SendController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("remitout")
    public void send() {
        for (int i = 0; i < 2; i++) {
            CorrelationData s1, s2;
            String o1 = "saerewqrewo" + i, o2 = "aaaaaaaa" + i;
//            rabbitTemplate.convertAndSend(REMIT_OUT_EXCHANGE, REMIT_OUT_ROUTING_KEY, o1, s1 = getCorrelationData());
            DataSource.DataWrapper<String> dataWrapper = new DataSource.DataWrapper<String>();
//            dataWrapper.setEntity(o1);
//            dataWrapper.setType(String.class);
//            DataSource.MAPPING.putIfAbsent(s1.getId(), dataWrapper);

            rabbitTemplate.convertAndSend(REMIT_IN_EXCHANGE, REMIT_IN_ROUTING_KEY, o2, s2 = getCorrelationData());
            dataWrapper = new DataSource.DataWrapper<String>();
            dataWrapper.setEntity(o2);
            dataWrapper.setType(String.class);
            DataSource.MAPPING.putIfAbsent(s2.getId(), dataWrapper);
        }
    }

    private CorrelationData getCorrelationData() {
        return new CorrelationData(UUID.randomUUID().toString());
    }
}
