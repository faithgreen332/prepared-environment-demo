package com.funpay.management.controller;

import com.funpay.common.enums.CallStatus;
import com.funpay.management.model.request.VatRateVO;
import com.funpay.management.service.VatRateService;
import com.funpay.model.response.FunpayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@RestController
@RequestMapping("vat/rate")
public class VatRateController {

    @Autowired
    VatRateService vatRateService;

    @PostMapping
    public FunpayResult saveOne(@RequestBody @Validated VatRateVO vo) {
        int i = vatRateService.saveOne(vo);
        return i > 0 ?
                FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), i) :
                FunpayResult.result(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue(), 0);
    }
}
