package com.funpay.usercenter.model;

import java.io.Serializable;
import lombok.Data;

/**
 * t_account_balance
 * @author 
 */
@Data
public class TAccountBalance implements Serializable {
    /**
     * 用户id
     */
    private Integer merchantId;

    /**
     * 余额
     */
    private String balance;

    /**
     * 冻结余额
     */
    private String freezeBalance;

    /**
     * 提现余额
     */
    private Long applyBalance;

    /**
     * 版本
     */
    private Long version;

    /**
     * 加密字段版本
     */
    private String versionSign;

    private static final long serialVersionUID = 1L;
}