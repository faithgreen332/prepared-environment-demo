package com.funpay.usercenter.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_request_transfer
 * @author 
 */
@Data
public class TRequestTransfer implements Serializable {
    private Integer id;

    private Integer merchantId;

    private Integer businessId;

    private String recordTime;

    private Integer amount;

    private String currency;

    private String orderNo;

    private String tradeNo;

    private String payWay;

    private String returnUrl;

    private String bankNo;

    private String bankBranch;

    private String accNo;

    private String accName;

    private String memo;

    private Date createTime;

    private Date updateTime;

    /**
     * 实际扣款金额
     */
    private Integer actualAmount;

    /**
     * 交易状态
     */
    private Integer status;

    /**
     * 交易描述
     */
    private String mess;

    /**
     * 版本
     */
    private String version;

    private String bankLocation;

    private Integer accountType;

    private String clientId;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 身份证号
     */
    private String idNo;

    /**
     * 增值税
     */
    private Integer vat;

    private static final long serialVersionUID = 1L;
}