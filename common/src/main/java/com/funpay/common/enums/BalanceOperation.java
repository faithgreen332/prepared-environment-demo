package com.funpay.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 余额操作 2018-3-8 18:57:35
 *
 * @author dave
 */
@Getter
@AllArgsConstructor
public enum BalanceOperation {

    /**
     * 查询余额
     */
    BALANCE_QUERY(0, "query"),

    /**
     * 增加余额
     */
    BALANCE_ADD(1, "add"),

    /**
     * 余额减去
     */
    BALANCE_MINUS(2, "minus"),

    /**
     * 冻结余额
     */
    FREEZE(3, "freeze"),

    /**
     * 征信冻结
     */
    CREDIT_FREEZE(4, "Credit freeze"),

    /**
     * Vietinbank新增余额操作
     *
     * @author dave
     * @update 新增业务
     * @date 2019-4-22
     */
    VIETINBANK_ADD(5, "Vietinbank add"),

    /**
     * 完成打款 消费冻结余额
     */
    unfreeze_end(6, "unfreeze end"),

    /**
     * 打款失败 解除冻结余额
     */
    unfreeze_reduction(7, "unfreeze reduction"),

    /**
     * 提现
     */
    withdraw(8, "withdraw"),

    /**
     * 提现完成
     */
    withdraw_end(9, "withdraw_end"),

    ;
    private int code;
    private String name;

    public static BalanceOperation getBalanceOperation(int code) {
        for (BalanceOperation statusCode : BalanceOperation.values()) {
            if (statusCode.getCode() == code) {
                return statusCode;
            }
        }
        return BalanceOperation.BALANCE_QUERY;
    }

    public static BalanceOperation getBalanceOperation(String name) {
        for (BalanceOperation statusCode : BalanceOperation.values()) {
            if (statusCode.getName().equals(name)) {
                return statusCode;
            }
        }
        return BalanceOperation.BALANCE_QUERY;
    }
}
