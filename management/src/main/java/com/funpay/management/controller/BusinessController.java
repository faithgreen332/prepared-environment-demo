package com.funpay.management.controller;

import com.funpay.common.enums.CallStatus;
import com.funpay.management.model.request.BusinessVO;
import com.funpay.management.service.impl.BusinessServiceImpl;
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
@RequestMapping("business")
public class BusinessController {

    @Autowired
    BusinessServiceImpl businessService;

    @PostMapping
    public FunpayResult saveOne() {
        int count = businessService.saveOne();
        return count > 0 ? FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), count) :
                FunpayResult.resultNullData(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue());
    }
}
