package com.funpay.management.mapper;


import com.funpay.model.dto.TbStepFee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbStepFeeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbStepFee record);

    int insertSelective(TbStepFee record);

    TbStepFee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbStepFee record);

    int updateByPrimaryKey(TbStepFee record);
}