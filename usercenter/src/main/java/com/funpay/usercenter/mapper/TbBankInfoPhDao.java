package com.funpay.usercenter.mapper;

import com.funpay.usercenter.model.TbBankInfoPh;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbBankInfoPhDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBankInfoPh record);

    int insertSelective(TbBankInfoPh record);

    TbBankInfoPh selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbBankInfoPh record);

    int updateByPrimaryKey(TbBankInfoPh record);
}