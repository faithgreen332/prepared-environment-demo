package com.funpay.demo.controller;

import com.funpay.demo.vo.TestVo;
import com.funpay.exception.constant.enums.BusinessResponseEnum;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/3/9
 **/
@RestController
public class DemoController {

    @PostMapping("/{id}")
    public String checkNotNull(@PathVariable("id") int id) {
        Object a = null;
        BusinessResponseEnum.BUSINESS_ERROR.assertNotNull(a);
        return "aa0";
    }

    @PostMapping("notnull")
    public Object voTest(@RequestBody @Validated TestVo vo) {
        return null;
    }
}
