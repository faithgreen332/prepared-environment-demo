package com.funpay.nacosserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/2/22
 **/
@RestController
@RequestMapping("user")
@RefreshScope
public class UserCenterController {

    @Value("${user.name}")
    private String name;

    @GetMapping("faith")
    public String get() {
        return "hello" + name;
    }
}
