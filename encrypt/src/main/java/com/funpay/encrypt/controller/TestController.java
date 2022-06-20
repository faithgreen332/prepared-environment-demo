package com.funpay.encrypt.controller;

import com.funpay.encrypt.AESUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@RestController
public class TestController {

    @GetMapping("get")
    public void getKey() {

        System.out.println(AESUtil.Atomic_secret_key.get());
    }

    @PostMapping("post")
    public void setKey() {
        AESUtil.Atomic_secret_key.set("aaaaa");
    }
}
