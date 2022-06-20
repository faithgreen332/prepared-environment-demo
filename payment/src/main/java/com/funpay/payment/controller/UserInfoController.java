package com.funpay.payment.controller;

import com.funpay.api.UserInfoApi;
import com.funpay.model.response.FunpayResult;
import com.funpay.model.user.UserInfo;
import com.funpay.payment.configuration.RestTemplateConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Leeko
 * @date 2022/1/26
 **/
@RestController
public class UserInfoController implements UserInfoApi {

    @Autowired
    @Qualifier(RestTemplateConfiguration.HTTP_TEMPLATE)
    RestTemplate restTemplate;

    @PostMapping("userInfo")
    @Override
    public FunpayResult addUserInfo(@RequestBody @Validated UserInfo userInfo) {
        return new FunpayResult();
    }

    @PostMapping()
    public String checkNotNull(@RequestBody UserInfo userInfo) {
        return "";
    }
}
