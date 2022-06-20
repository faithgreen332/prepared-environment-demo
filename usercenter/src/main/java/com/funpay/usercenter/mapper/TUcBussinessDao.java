package com.funpay.usercenter.mapper;


import com.funpay.usercenter.model.TUcBussiness;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TUcBussinessDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TUcBussiness record);

    int insertSelective(TUcBussiness record);

    TUcBussiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUcBussiness record);

    int updateByPrimaryKey(TUcBussiness record);

    @Select("select COUNT(*) from t_uc_bussiness where merchant_id=#{merchantID} and id=#{bussinessID}")
    Long checkBusinessId(@Param("merchantID") int merchantID, @Param("bussinessID") int bussinessID);
}