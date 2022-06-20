package com.funpay.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 打款状态码
 *
 * @author dave
 */
@Getter
@AllArgsConstructor
public enum TransferStatusCode {

    /**
     * 成功
     */
    SUCCESS(200, "Success"),

    /**
     * 等待
     */
    PENDING(99, "pending"),

    /**
     * 失败
     */
    FAIL(11, "fail"),

    /**
     * 处理 IMEDIATECH 侧时出错
     */
    ERROR(101, "Error during proceeding"),

    /**
     * RequestId是相同的
     */
    REQUEST_ID_REPEAT(102, "RequestId repeat"),

    /**
     * 签名不正确
     */
    SIGNATURE_INCORRECT(103, "Signature is incorrect"),

    /**
     * 客户账户不正确
     */
    PARTNER_CODE_INCORRECT(110, "PartnerCode is incorrect"),

    /**
     * 客户账户不存在
     */
    PARTNER_CODE_NOT_EXIST(111, "PartnerCode has been removed from the system"),

    /**
     * 客户账户未激活
     */
    PARTNER_CODE_NOT_ACTIVATED(112, "PartnerCode has not been activated"),

    /**
     * 需要操作代码oper
     */
    OPERATION(113, "Operation code is required"),

    /**
     * 操作代码不正确
     */
    OPERATION_INCORRECT(114, "Operation Code incorrect"),

    /**
     * 银行代码是必需的
     */
    BANK_INCORRECT(115, "Bank code is required"),

    /**
     * 银行代码不支持
     */
    BANK_NOT_SUPPORT(116, "Bank code does not support"),

    /**
     * 帐号/强制卡号长度为8->22个字符
     */
    ACCOUNT_NUMBER_CHARACTERS(117, "Account number / mandatory card number is 8-> 22 characters long"),

    /**
     * 收款人姓名无效
     */
    NAME_INVALID(118, "The recipient's name is invalid"),

    /**
     * 帐号/卡号不存在
     */
    ACCOUNT_NOT_EXIST(119, "Account number / card number does not exist"),

    /**
     * 帐户类型不正确
     */
    INCORRECT_TYPE(120, "Incorrect account type"),

    /**
     * 交易不存在
     */
    TRANSACTION_NOT_FIND(121, "The system did not find a transaction"),

    /**
     * 交易非法
     */
    ILLEGAL(123, "Invalid account no"),

    /**
     * 备注非法
     */
    REMARK_ILLEGAL(124, "Remark is illegal"),

    /**
     * 余额不足,去除imedia 129 余额不足
     */
    NOT_ENOUGH_LIMIT(13, "Not enough limit for spending or guarantee expired"),

    /**
     * 交易过期
     */
    TRANSACTION_EXPIRED(130, "Transaction expired"),

    /**
     * 交易进行中
     */
    TRANSACTION_RUN(14, "run"),

    /**
     * 交易进行中
     */
    TRANSACTION_BEGIN(12, "run"),

    /**
     * 日限
     */
    TRANSACTION_DAY_LIMIT(131, "Transaction expired");
    private int code;

    private String value;

    public static TransferStatusCode getTransferCode(int code) {
        for (TransferStatusCode transferCode : TransferStatusCode.values()) {
            if (transferCode.getCode() == code) {
                return transferCode;
            }
        }
        return TransferStatusCode.PENDING;
    }
}