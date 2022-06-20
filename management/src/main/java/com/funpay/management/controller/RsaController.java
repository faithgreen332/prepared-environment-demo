package com.funpay.management.controller;

import com.funpay.common.enums.CallStatus;
import com.funpay.management.service.RsaService;
import com.funpay.model.response.FunpayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@RestController
@RequestMapping("rsa")
public class RsaController {

    @Autowired
    RsaService rsaService;

    @PostMapping
    public FunpayResult saveOne() {
        int count = rsaService.saveOne();
        return count > 0 ? FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), count)
                : FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
    }
}
