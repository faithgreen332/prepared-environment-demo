package com.funpay.management.mapper;

import com.funpay.model.dto.TbRsa;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbRsaDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbRsa record);

    int insertSelective(TbRsa record);

    TbRsa selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbRsa record);

    int updateByPrimaryKey(TbRsa record);
}