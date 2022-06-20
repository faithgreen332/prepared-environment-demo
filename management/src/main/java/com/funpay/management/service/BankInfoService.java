package com.funpay.management.service;

import com.funpay.management.mapper.TbBankInfoDao;
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
public class BankInfoService {

    @Autowired
    TbBankInfoDao bankInfoDao;

    public int saveOne() {
        TbBankInfo bankInfo = new TbBankInfo();
        bankInfo.setBankCode("1001");
        bankInfo.setBankName("faith测试用假银行就叫你faith银行");
        bankInfo.setAbbr("faith银行");
        bankInfo.setPayWay("5001");
        bankInfo.setAccountingPeriod(3);
        return bankInfoDao.insertSelective(bankInfo);
    }
}
