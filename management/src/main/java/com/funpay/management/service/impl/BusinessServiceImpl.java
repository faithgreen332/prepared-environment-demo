package com.funpay.management.service.impl;

import com.alibaba.fastjson.JSON;
import com.funpay.common.sign.RsaUtils;
import com.funpay.common.utils.KeyHolder;
import com.funpay.management.mapper.TBusinessDao;
import com.funpay.model.dto.TBusiness;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Service
@Slf4j
public class BusinessServiceImpl {

    @Autowired
    TBusinessDao businessDao;


    public int saveOne() {
        TBusiness business = new TBusiness();
        business.setMerchantId(1);
        business.setBusinessName("faith本地测试用业务");
        business.setBusinessType("1");
        business.setBusinessPlatform("3");
        business.setVatRateId(4);
        business.setStepFeeId(1);
        business.setReturnUrl("http://xxooo.com");
        business.setBusinessRsaPubKey("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw1ayyGtI9/0eqidnenfV\n" +
                "/BkYsDIW8kNzxexLon2XtobenHdM7mH2Y7XmuxuNjQMsXCAKCdjwYlfWOMVSHodu\n" +
                "ZAEDMyxRvzyzfadY3/AzYdKo5vHggXur5VoednTQhLi6S1ei3+lRlM1oeT1aj8mL\n" +
                "goVr+X3VnjhloTnP+418lE6kWgvk8wQpiBk8ZbXGLVhSwer2dV+rJ+gg5DAthCEG\n" +
                "dLVIjA8DaAAg2o77quYx72LF76PyyVvXvWGFZyO6Bw8oM6941X2YOQ2Un21/w1Gk\n" +
                "W3Kj4Z6f6XvOA2b+QW6LnvYze35o2T4oElbnEGDMpERVZCdvLNy+NKEtHHC8/RHQ\n" +
                "4wIDAQAB\n" +
                "-----END PUBLIC KEY-----");
        businessDao.insertSelective(business);
        String signVersion = KeyHolder.KEY_MAP.get(KeyHolder.KEY_SYS_RSA_SIGN_KEY_VERSION);
        String signKey = KeyHolder.KEY_MAP.get(KeyHolder.KEY_SYS_RSA_SIGN_PRI_PREFIX + signVersion);
        business.setSign(RsaUtils.signFromStr(JSON.toJSONString(business), RsaUtils.getPrivateKey(signKey)));
        return businessDao.updateByPrimaryKeySelective(business);
    }
}
