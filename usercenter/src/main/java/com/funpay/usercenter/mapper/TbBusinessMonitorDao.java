package com.funpay.usercenter.mapper;


import com.funpay.usercenter.model.TbBusinessMonitor;

public interface TbBusinessMonitorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBusinessMonitor record);

    int insertSelective(TbBusinessMonitor record);

    TbBusinessMonitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbBusinessMonitor record);

    int updateByPrimaryKey(TbBusinessMonitor record);
}