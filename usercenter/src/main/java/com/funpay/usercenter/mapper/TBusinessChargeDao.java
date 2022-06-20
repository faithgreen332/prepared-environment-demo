package com.funpay.usercenter.mapper;


import com.funpay.usercenter.model.TBusinessCharge;

public interface TBusinessChargeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TBusinessCharge record);

    int insertSelective(TBusinessCharge record);

    TBusinessCharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBusinessCharge record);

    int updateByPrimaryKey(TBusinessCharge record);
}