package com.funpay.mail.vo;

import lombok.Data;

/**
 * @author Leeko
 * @date 2022/3/3
 **/
@Data
public class MailEntity {

    private String subject;
    private String text;
    private String from;
    private String to;
}
