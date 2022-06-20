package com.funpay.management.mapper;


import com.funpay.model.dto.TbBankInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbBankInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBankInfo record);

    int insertSelective(TbBankInfo record);

    TbBankInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbBankInfo record);

    int updateByPrimaryKey(TbBankInfo record);
}