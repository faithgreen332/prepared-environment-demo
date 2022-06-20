package com.funpay.usercenter.mapper;

import com.funpay.usercenter.model.TAccountBalance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TAccountBalanceDao {
    int deleteByPrimaryKey(Integer merchantId);

    int insert(TAccountBalance record);

    int insertSelective(TAccountBalance record);

    TAccountBalance selectByPrimaryKey(Integer merchantId);

    int updateByPrimaryKeySelective(TAccountBalance record);

    int updateByPrimaryKey(TAccountBalance record);

    /**
     * 取出来余额
     *
     * @param decryptKey aes 加密的 key
     * @param merchantId merchantId
     * @return 解密后的余额
     */
    @Select("select AES_DECRYPT(UNHEX(`balance`), #{decryptKey}) from t_account_balance where merchant_id=#{merchantId}")
    Long decryptBalance(@Param("decryptKey") String decryptKey, @Param("merchantId") Integer merchantId);

    /**
     * 幂等接口，更新余额
     *
     * @param amount     更新的大小
     * @param merchantId 商户id
     * @param version    幂等保护值
     * @param decryptKey 解密 key
     * @return 更新了多少条
     */
    @Update("update t_account_balance set version=version + 1, balance = HEX(AES_ENCRYPT(AES_DECRYPT(UNHEX(`balance`), #{decryptKey}) - #{amount}, #{decryptKey})), freeze_balance = HEX(AES_ENCRYPT(AES_DECRYPT(UNHEX(`freeze_balance`), #{decryptKey}) + #{amount}, #{decryptKey})) where merchant_id=#{merchantId} and version=#{version} and AES_DECRYPT(UNHEX(`balance`), #{decryptKey}) >= #{amount}")
    int freezeAmount(@Param("amount") Long amount, @Param("merchantId") int merchantId, @Param("version") Long version, @Param("decryptKey") String decryptKey);
}