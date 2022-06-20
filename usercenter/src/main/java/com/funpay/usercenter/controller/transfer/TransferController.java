package com.funpay.usercenter.controller.transfer;

import com.funpay.model.request.TransferReq;
import com.funpay.model.response.FunpayResult;
import com.funpay.usercenter.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leeko
 * @date 2022/2/14
 **/
@RestController("transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @PostMapping
    public FunpayResult transfer(@RequestBody TransferReq transferReq) {
        return null;
    }

    @PostMapping(value = "/freeze", consumes = MediaType.APPLICATION_JSON_VALUE)
    FunpayResult freeze(@RequestBody TransferReq transferReq) {
        return transferService.freeze(transferReq);
    }
}
