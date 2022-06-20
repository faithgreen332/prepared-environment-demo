package com.funpay.usercenter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alen
 */
@Slf4j
public class FeeUtils {

    private static final String GCASH_ONLINE = "GCASH_ONLINE";
    private static final String INSTAPAY = "INSTAPAY";
    private static final String PESONET = "PESONET";
    private static final String UB_ONLINE = "UB_ONLINE";


    private static final String BPI_ONLINE = "BPI_ONLINE";
    private static final String RCBC_ONLINE = "RCBC_ONLINE";
    private static final String GRABPAY_ONLINE = "GRABPAY_ONLINE";


    private static final String DRAGONPAY_OTC = "DRAGONPAY_OTC";

    public static BigDecimal calculationFee(String feeRules, Long amount) {
        //获取当前计费规则的 费率，阶梯手续费值，和费率级别
        AtomicLong fee = new AtomicLong();
        AtomicLong rate = new AtomicLong();
        AtomicLong policeId = new AtomicLong();


        JSONObject jsonObject = JSON.parseObject(feeRules);

        TieredFeeRules tieredFeeRules = JSON.toJavaObject(jsonObject, TieredFeeRules.class);

        tieredFeeRules.getFeePolicyList().forEach(k -> {
            /*
             * 默认计费采用第一阶梯
             */
            if (k.getPolicyId() == 1) {
                fee.set(k.getAmount());
                policeId.set(k.getPolicyId());
                rate.set(k.getRate());
            }

            if (amount >= k.getUpperLimit()) {
                fee.set(k.getAmount());
                policeId.set(k.getPolicyId());
                rate.set(k.getRate());
            }
        });

        //finalFee= fee+ amount *rate （万分之N）
        BigDecimal finalFee = new BigDecimal(0);
        finalFee = finalFee.add(new BigDecimal(fee.get()));
        finalFee = finalFee.add(new BigDecimal(amount)
                .multiply(new BigDecimal(rate.get()))
                .divide(new BigDecimal(10000), 2, RoundingMode.HALF_UP));


        log.info("金额: {} ,费率阶梯: {}, 最终手续费: {} ,费率比例 : {}", amount, policeId.get(), fee.get(), rate.get());
        return finalFee;
    }


//    public static BigDecimal calculationOnlineFee(String feeRules, Long amount, String productType) {
//
//        JSONObject jsonObject = JSON.parseObject(feeRules);
//
//        OnlineTieredFeeRules onlineTieredFeeRules = JSON.toJavaObject(jsonObject, OnlineTieredFeeRules.class);
//
//        if (GCASH_ONLINE.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getGcashOnline()), amount);
//        }
//        if (INSTAPAY.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getInstaPay()), amount);
//        }
//        if (PESONET.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getPesoNet()), amount);
//        }
//        if (UB_ONLINE.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getUbOnline()), amount);
//        }
//        if (BPI_ONLINE.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getBpiOnline()), amount);
//        }
//        if (RCBC_ONLINE.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getRcbcOnline()), amount);
//        }
//        if (GRABPAY_ONLINE.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(onlineTieredFeeRules.getGrabpayOnline()), amount);
//        }
//
//        //为0表示出现异常
//        return new BigDecimal(0);
//    }

//    public static BigDecimal calculationOfflineFee(String feeRules, Long amount, String productType) {
//
//        JSONObject jsonObject = JSON.parseObject(feeRules);
//
//        OfflineTieredFeeRules offlineTieredFeeRules = JSON.toJavaObject(jsonObject, OfflineTieredFeeRules.class);
//
//
//        if (DRAGONPAY_OTC.equals(productType)) {
//            log.info("选择的计费产品是: {}", productType);
//            return calculationFee(JSON.toJSONString(offlineTieredFeeRules.getDragonPay()), amount);
//        }
//        //为0表示出现异常
//        return new BigDecimal(0);
//    }

//    public static void main(String[] args) {
//        String str = "{\"gcashOnline\":{\"feePolicyNum\":5,\"feePolicyList\":[{\"amount\":100,\"policyId\":1,\"rate\":1,\"upperLimit\":20000},{\"amount\":400,\"policyId\":2,\"upperLimit\":40000},{\"amount\":600,\"policyId\":3,\"upperLimit\":60000},{\"amount\":800,\"policyId\":4,\"upperLimit\":80000},{\"amount\":1000,\"policyId\":5,\"upperLimit\":120000}]},\"instaPay\":{\"feePolicyNum\":5,\"feePolicyList\":[{\"amount\":200,\"policyId\":1,\"upperLimit\":20000},{\"amount\":400,\"policyId\":2,\"upperLimit\":40000},{\"amount\":600,\"policyId\":3,\"upperLimit\":60000},{\"amount\":800,\"policyId\":4,\"upperLimit\":80000},{\"amount\":1000,\"policyId\":5,\"upperLimit\":120000}]},\"ubOnline\":{\"feePolicyNum\":5,\"feePolicyList\":[{\"amount\":300,\"policyId\":1,\"upperLimit\":20000},{\"amount\":400,\"policyId\":2,\"upperLimit\":40000},{\"amount\":600,\"policyId\":3,\"upperLimit\":60000},{\"amount\":800,\"policyId\":4,\"upperLimit\":80000},{\"amount\":1000,\"policyId\":5,\"upperLimit\":120000}]},\"pesoNet\":{\"feePolicyNum\":5,\"feePolicyList\":[{\"amount\":400,\"policyId\":1,\"upperLimit\":20000},{\"amount\":500,\"policyId\":2,\"upperLimit\":40000},{\"amount\":600,\"policyId\":3,\"upperLimit\":60000},{\"amount\":800,\"policyId\":4,\"upperLimit\":80000},{\"amount\":1000,\"policyId\":5,\"upperLimit\":120000}]}}";
//
//        calculationOnlineFee(str, 20000L, "UB_ONLINE");
//        calculationOnlineFee(str, 20000L, "PESONET");
//        calculationOnlineFee(str, 20000L, "INSTAPAY");
//        calculationOnlineFee(str, 2000L, "GCASH_ONLINE");
//    }

}

class TieredFeeRules {
    private int feePolicyNum;
    private List<FeePolicyList> feePolicyList;

    public int getFeePolicyNum() {
        return feePolicyNum;
    }

    public void setFeePolicyNum(int feePolicyNum) {
        this.feePolicyNum = feePolicyNum;
    }

    public List<FeePolicyList> getFeePolicyList() {
        return feePolicyList;
    }

    public void setFeePolicyList(List<FeePolicyList> feePolicyList) {
        this.feePolicyList = feePolicyList;
    }
}

class FeePolicyList {
    private int rate;
    private int amount;
    private int policyId;
    private int upperLimit;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }
}
