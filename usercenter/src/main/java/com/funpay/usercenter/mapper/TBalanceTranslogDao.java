package com.funpay.usercenter.mapper;


import com.funpay.usercenter.model.TBalanceTranslog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TBalanceTranslogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TBalanceTranslog record);

    int insertSelective(TBalanceTranslog record);

    TBalanceTranslog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBalanceTranslog record);

    int updateByPrimaryKey(TBalanceTranslog record);
}