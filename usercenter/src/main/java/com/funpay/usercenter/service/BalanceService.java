package com.funpay.usercenter.service;

import com.funpay.common.enums.BalanceOperResult;
import com.funpay.encrypt.config.SysDataEdt;
import com.funpay.usercenter.mapper.TAccountBalanceDao;
import com.funpay.usercenter.model.TAccountBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Leeko
 * @date 2022/2/17
 **/
@Service
@Slf4j
public class BalanceService {

    @Autowired
    TAccountBalanceDao tAccountBalanceDao;

    /**
     * 改变用户的余额，先取出来余额比较下钱够不够转，够的话就 update，否则返回
     * 为什么不在 transfer 一开始就判断呢，如果余额不够了能省了这么远的流程
     * TODO 先写代码吧，回头再改
     * 源代码这里用了递归调用更新，最多递归 52 次，感觉没有必要，这里主要操作是取 aes 解密的 key 和操作数据库，如果失败了递归一万次也是一样的结果
     *
     * @param merchantId merchantId
     * @param amount     amount
     * @return 状态码
     */
    public int freezeAmount(int merchantId, Long amount) {
        TAccountBalance accountBalance = tAccountBalanceDao.selectByPrimaryKey(merchantId);

        // 获取余额
        String decryptKey;
        int updatedCount;
        try {
            decryptKey = SysDataEdt.getInstance().getDecryptMap(accountBalance.getVersionSign());
            decryptKey = merchantId + decryptKey;

            updatedCount = tAccountBalanceDao.freezeAmount(amount, merchantId, accountBalance.getVersion(), decryptKey);
        } catch (Exception e) {
            // TODO 缺少邮件通知
            log.error("余额更新失败，可能是解密 key 没取到：" + e);
            return BalanceOperResult.FREEZE_FAIL.getCode();
        }

        // 如果没抛异常，又没有更新到数据，应该是余额不够了
        return updatedCount == 0 ? BalanceOperResult.INSUFFICIENT_BALANCE.getCode() : BalanceOperResult.SUCCESS.getCode();
    }
}
