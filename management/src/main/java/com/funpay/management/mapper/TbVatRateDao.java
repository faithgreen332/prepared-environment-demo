package com.funpay.management.mapper;

import com.funpay.model.dto.TbVatRate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbVatRateDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbVatRate record);

    int insertSelective(TbVatRate record);

    TbVatRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbVatRate record);

    int updateByPrimaryKey(TbVatRate record);
}