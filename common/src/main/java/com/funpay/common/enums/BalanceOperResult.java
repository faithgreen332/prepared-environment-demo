package com.funpay.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 余额操作 操作结果
 *
 * @author dave
 */
@Getter
@AllArgsConstructor
public enum BalanceOperResult {

    /**
     * 操作成功
     */
    SUCCESS(0),

    /**
     * 余额不足
     */
    INSUFFICIENT_BALANCE(1),

    /**
     * 更新失败
     */
    UPDATE_FAIL(2),

    /**
     * 冻结失败
     */
    FREEZE_FAIL(3),

    /**
     * 未知异常
     */
    UNKNOWN_ERROR(4),

    /**
     * 非法操作：一般是设置值的时候给了不在这个范围内的值
     */
    ILLEGAL_OPERATION(5);

    private int code;

    public static BalanceOperResult getBalanceOperResult(int result) {
        for (BalanceOperResult balanceOperResult : BalanceOperResult.values()) {
            if (balanceOperResult.getCode() == result) {
                return balanceOperResult;
            }
        }
        return BalanceOperResult.ILLEGAL_OPERATION;
    }
}
