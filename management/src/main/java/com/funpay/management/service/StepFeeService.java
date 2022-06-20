package com.funpay.management.service;

import com.funpay.management.mapper.TbStepFeeDao;
import com.funpay.management.model.request.StepFeeVO;
import com.funpay.model.dto.TbStepFee;
import com.funpay.model.response.FunpayResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Service
@Slf4j
public class StepFeeService {

    @Autowired
    TbStepFeeDao stepFeeDao;

    public int saveOne(StepFeeVO vo) {
        TbStepFee stepFee = new TbStepFee();
        stepFee.setDescription(vo.getDescription());
        stepFee.setValue(vo.getValue());
        return stepFeeDao.insertSelective(stepFee);
    }

    public TbStepFee findOne(int id) {
        return stepFeeDao.selectByPrimaryKey(id);
    }
}
