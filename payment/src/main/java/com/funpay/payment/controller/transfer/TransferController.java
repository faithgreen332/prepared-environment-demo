package com.funpay.payment.controller.transfer;

import com.funpay.api.transfer.TransferApi;
import com.funpay.common.enums.CallStatus;
import com.funpay.common.enums.SystemUrl;
import com.funpay.common.executors.AsyncExecutor;
import com.funpay.common.sign.SysSign;
import com.funpay.model.request.TransferReq;
import com.funpay.model.response.FunpayResult;
import com.funpay.model.response.TransferRes;
import com.funpay.payment.feign.UcTransferService;
import com.funpay.redis.config.FunRedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.TimerTask;

/**
 * @author Leeko
 * @date 2022/2/14
 **/
@RestController("transfer/api")
public class TransferController implements TransferApi {

    @Autowired
    UcTransferService ucTransferService;

    @Autowired
    FunRedisClient redisClient;

    @PostMapping("transferMoney")
    @Override
    public FunpayResult apiTransferMoney(@RequestBody @Validated TransferReq transferReq) {

        // 内部请求 uc，要用内部的私钥和 key 签名
        transferReq.setSign(SysSign.apiSignInternal(redisClient.get((RedisPrefix.SYS_INTERNAL_CANCAL_PRIVATE)), redisClient.get(SystemUrl.INTERNAL_KEY.getName()), transferReq));

        // 1.请求 uc ，生成订单信息,如果 uc 生成订单出错了，直接返回
        FunpayResult uctransferResult = ucTransferService.transfer(transferReq);
        if (uctransferResult.getCode() != CallStatus.SUCCESS.getCode()) {
            return uctransferResult;
        }

        TransferRes transferRes = (TransferRes) uctransferResult.getData();
        // 2.异步打款
        asyncTransfer(transferReq, transferRes);

        // 3.构造返回数据，加上验签

        // 4.返回结果
        return null;
    }

    /**
     * 冻结余额，调用银行扣款接口
     *
     * @param transferReq transferReq
     * @param transferRes transferRes
     */
    private void asyncTransfer(TransferReq transferReq, TransferRes transferRes) {
        AsyncExecutor.getInstance().execute(new TimerTask() {
            @Override
            public void run() {
                FunpayResult freezeResult = ucTransferService.freeze(transferReq);
                String transactionTime = null;
                if (CallStatus.SUCCESS.getCode() != freezeResult.getCode() || callBankApi(transferReq, transferRes)) {
                    // 通知 uc 冻结余额状态
                    noticeUcFreezeResult(transferRes.getTradeNo(), freezeResult.getCode(), transactionTime);
                }

                // 2.构造参数调用银行的接口进行扣款 TODO


            }

            private void noticeUcFreezeResult(String tradeNo, int code, Object o) {
                // 如果这个是0说明没有还没到调用银行扣款接口就失败了
                if (o == null) {

                } else {

                }
            }

            private boolean callBankApi(TransferReq transferReq, TransferRes transferRes) {
                return false;
            }
        });
    }
}

interface RedisPrefix {
    /**
     * 业务内部系统-内部私密密
     */
    String SYS_INTERNAL_CANCAL_PRIVATE = "sys_internal_cancal_private";
}