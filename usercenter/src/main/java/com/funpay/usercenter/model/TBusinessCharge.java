package com.funpay.usercenter.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_business_charge
 * @author 
 */
@Data
public class TBusinessCharge implements Serializable {
    private Integer id;

    private Integer merchantId;

    private Integer businessId;

    private Integer feeId;

    /**
     * 业务系统唯一编号
     */
    private String businessCode;

    /**
     * 0.00-9999999.99VND * 100
     */
    private Integer chargeAmount;

    /**
     * 0-99999 
     */
    private Integer chargeRate;

    /**
     * 0-99999 服务费
     */
    private Integer chargeService;

    /**
     * 0-99999 租金
     */
    private Integer chargeLease;

    private Integer status;

    /**
     * 是否支持 0 (true)支持 1 (false)不支持 
     */
    private Boolean flag;

    /**
     * 是否自动提款 默认手动
     */
    private Boolean autoWithdraw;

    /**
     * 下次执行时间
     */
    private Date executionTime;

    /**
     * 0:自然日 1：自然周 2：自然月  3 ：自然年, 自然周月年值每周、月、年的第一天
     */
    private String periodType;

    /**
     * 0-99个period_type 
     */
    private String periodValue;

    /**
     * 0:自然日 1：自然周 2：自然月  3：自然年
     */
    private String periodLayType;

    /**
     * 0-99个period_lay_type 
     */
    private String periodLayValue;

    private Integer purviewFlag;

    /**
     * 回调地址
     */
    private String callback;

    private Date createTime;

    private Date updateTime;

    /**
     * rsa签名版本
     */
    private String rsaSignVersion;

    /**
     * rsa签名数据
     */
    private String rsaSignData;

    /**
     * 阶梯计费规则
     */
    private Object tieredFeeRules;

    /**
     * 支持的类型，bankrt，banknrt，电子钱包
     */
    private String supportType;

    private static final long serialVersionUID = 1L;
}