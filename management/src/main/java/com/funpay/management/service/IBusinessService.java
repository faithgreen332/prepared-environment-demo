package com.funpay.management.service;

import com.funpay.model.dto.TBusiness;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
public interface IBusinessService {

    /**
     * 写进一条记录
     *
     * @param bussiness business
     * @return 成功入库的条数
     */
    int saveOne(TBusiness bussiness);

    /**
     * 查询一个
     *
     * @param id id
     * @return TBusiness
     */
    TBusiness selectByPrimaryKey(Integer id);
}
