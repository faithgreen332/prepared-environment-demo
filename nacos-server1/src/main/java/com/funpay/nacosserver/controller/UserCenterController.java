package com.funpay.nacosserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leeko
 * @date 2022/2/22
 **/
@RestController
@RequestMapping("user")
@RefreshScope
public class UserCenterController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("get")
    public String get() {
//        String url = String.format("http://%s:%s/user/?faith", serviceInstance.getHost(), serviceInstance.getPort());
//        System.out.println("request url: " + url);
        return restTemplate.getForObject("http://funpay-payment/user/faith", String.class);
    }
}
