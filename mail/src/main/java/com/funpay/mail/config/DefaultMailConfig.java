package com.funpay.mail.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
@Configuration
@Data
public class DefaultMailConfig {
    @Value("${funpay.mail.default.from}")
    private String defaultFrom;
    @Value("${funpay.mail.default.to}")
    private String defaultTo;
    @Value("${funpay.mail.default.subject}")
    private String defaultSubject;
    @Value("${funpay.mail.default.text}")
    private String defaultText;
}
