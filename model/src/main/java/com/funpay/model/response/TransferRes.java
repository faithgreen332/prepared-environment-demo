package com.funpay.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 结果响应类
 *
 * @author dave
 */
@Data
public class TransferRes implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 唯一交易号
     */
    private String tradeNo;
    /**
     * 银行唯一码对应
     */
    private String bankNoCode;

    /**
     * 银行分支唯一码对应
     */
    private String bankBranchCode;

    /**
     * 业务码
     */
    private String businessCode;

    private String productType;
}
