package com.funpay.management.mapper;


import com.funpay.model.dto.TBusiness;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TBusinessDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TBusiness record);

    int insertSelective(TBusiness record);

    TBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBusiness record);

    int updateByPrimaryKey(TBusiness record);
}