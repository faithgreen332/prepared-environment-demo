package com.funpay.management.controller;

import com.funpay.common.enums.CallStatus;
import com.funpay.management.model.request.StepFeeVO;
import com.funpay.management.service.StepFeeService;
import com.funpay.model.dto.TbStepFee;
import com.funpay.model.response.FunpayResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@RestController
@RequestMapping("stepfee")
public class StepFeeController {

    @Autowired
    StepFeeService stepFeeService;

    @PostMapping
    public FunpayResult saveOne(@RequestBody @Validated StepFeeVO vo) {
        int i = stepFeeService.saveOne(vo);
        return i > 0 ?
                FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), i) :
                FunpayResult.result(CallStatus.INNER_ERROR.getCode(), CallStatus.INNER_ERROR.getValue(), 0);
    }

    @GetMapping("/{id}")
    public FunpayResult findOne(@PathVariable("id") int id) {
        TbStepFee tbStepFee = stepFeeService.findOne(id);

        return FunpayResult.result(CallStatus.SUCCESS.getCode(), CallStatus.SUCCESS.getValue(), tbStepFee);
    }
}
