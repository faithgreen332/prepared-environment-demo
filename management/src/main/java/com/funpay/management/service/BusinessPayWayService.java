package com.funpay.management.service;

import com.funpay.management.mapper.TBusinessPayWayDao;
import com.funpay.management.mapper.TbBankInfoDao;
import com.funpay.model.dto.TBusinessPayWay;
import com.funpay.model.dto.TbBankInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Service
@Slf4j
public class BusinessPayWayService {

    @Autowired
    TBusinessPayWayDao businessPayWayDao;

    public int saveOne() {
        TBusinessPayWay businessPayWay = new TBusinessPayWay();
        businessPayWay.setPayWay("5001");
        businessPayWay.setBusinessId(3);
        businessPayWay.setMerchantId(1);
        return businessPayWayDao.insertSelective(businessPayWay);
    }
}
