package com.funpay.demo.dao;


import com.funpay.demo.dto.TMerchant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TMerchantDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TMerchant record);

    int insertSelective(TMerchant record);

    TMerchant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMerchant record);

    int updateByPrimaryKey(TMerchant record);

    List<TMerchant> findAll();
}