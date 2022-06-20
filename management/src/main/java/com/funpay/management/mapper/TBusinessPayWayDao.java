package com.funpay.management.mapper;


import com.funpay.model.dto.TBusinessPayWay;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TBusinessPayWayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TBusinessPayWay record);

    int insertSelective(TBusinessPayWay record);

    TBusinessPayWay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TBusinessPayWay record);

    int updateByPrimaryKey(TBusinessPayWay record);
}