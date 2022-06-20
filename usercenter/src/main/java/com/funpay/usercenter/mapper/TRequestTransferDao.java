package com.funpay.usercenter.mapper;


import com.funpay.usercenter.model.TRequestTransfer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TRequestTransferDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TRequestTransfer record);

    int insertSelective(TRequestTransfer record);

    TRequestTransfer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRequestTransfer record);

    int updateByPrimaryKey(TRequestTransfer record);

    @Select("select count(*) from `t_request_transfer` where order_no=#{orderNo} and merchant_id=#{merchantId}")
    Long checkTransferOrderNo(@Param("orderNo") String orderNo, @Param("merchantId") int merchantId);

    /**
     * 根据 tradeNo 查出交易请求记录
     *
     * @param tradeNo
     * @return
     */
    TRequestTransfer selectByTradeNo(String tradeNo);

    /**
     * 更新打款记录状态
     *
     * @param status 状态码
     * @param mess   状态描述
     * @param id     主键
     */
    @Update("update `t_request_transfer` set status=#{status}, mess=#{mess} where id=#{id}")
    void updateTransferStatus(@Param("status") int status, @Param("mess") String mess, @Param("id") Integer id);
}