package com.funpay.management.service;

import com.funpay.management.mapper.TbVatRateDao;
import com.funpay.management.model.request.VatRateVO;
import com.funpay.model.dto.TbVatRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Service
@Slf4j
public class VatRateService {

    @Autowired
    TbVatRateDao vatRateDao;

    public int saveOne(VatRateVO vo) {
        TbVatRate vatRate = new TbVatRate();
        vatRate.setDescription(vo.getDescription());
        vatRate.setValue(vo.getValue());
        return vatRateDao.insertSelective(vatRate);
    }
}
