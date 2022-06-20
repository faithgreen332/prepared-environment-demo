package com.funpay.payment.feign;

import com.funpay.model.request.TransferReq;
import com.funpay.model.response.FunpayResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Leeko
 * @date 2022/2/17
 **/
@FeignClient("funpay-usercenter")
@RequestMapping("/uc/transfer")
public interface UcTransferService {

    /**
     * 调用 uc 生成订单等一系列
     *
     * @param transferReq
     * @return
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    FunpayResult transfer(@RequestBody TransferReq transferReq);

    /**
     * 调用 uc 生成余额
     *
     * @param transferReq
     * @return
     */
    @PostMapping(value = "/freeze", consumes = MediaType.APPLICATION_JSON_VALUE)
    FunpayResult freeze(@RequestBody TransferReq transferReq);
}
