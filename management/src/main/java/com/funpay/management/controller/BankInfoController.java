package com.funpay.management.controller;

import com.funpay.common.enums.CallStatus;
import com.funpay.management.service.BankInfoService;
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
@RequestMapping("bankinfo")
public class BankInfoController {

    @Autowired
    BankInfoService bankInfoService;

    @PostMapping
    public FunpayResult saveOne() {
        int count = bankInfoService.saveOne();
        return count > 0 ? FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), count) :
                FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
    }
}
