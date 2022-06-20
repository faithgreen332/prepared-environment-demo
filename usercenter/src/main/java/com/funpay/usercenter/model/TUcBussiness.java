package com.funpay.usercenter.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_uc_bussiness
 * @author 
 */
@Data
public class TUcBussiness implements Serializable {
    private Integer id;

    private Integer merchantId;

    private String businessName;

    /**
     * 1:游戏 2：应用
     */
    private String businessType;

    /**
     * 1:Androdi 2:iOS 3:Web
     */
    private String businessPlatform;

    /**
     * 不同包名以,隔开
     */
    private String businessPackageName;

    /**
     * 签名md5
     */
    private String businessSign;

    /**
     * 证书路径
     */
    private String signPath;

    private Date createTime;

    private Date updateTime;

    /**
     * 0:正常 2：禁止支付 3：禁止结算 与商家的
     */
    private String status;

    private Date recoveryDate;

    /**
     * 0:关闭 1:Napas 2:VTC 4:payoo 可以用并运算兼容多个支付渠道
     */
    private Integer onlineFlag;

    /**
     * 0:自然日 2：自然周 3：自然月  4：自然年, 自然周月年值每周、月、年的第一天
     */
    private String onlinePeriodType;

    /**
     * 0-99个period_type 
     */
    private String onlinePeriodValue;

    /**
     * 0:自然日 2：自然周 3：自然月  4：自然年
     */
    private String onlinePeriodLayType;

    /**
     * 0-99个period_lay_type 
     */
    private String onlinePeriodLayValue;

    /**
     * 0.00-9999999.99VND * 100
     */
    private Integer onlinePayAmount;

    /**
     * 0-99999 
     */
    private Integer onlinePayRate;

    /**
     * 0:关闭 1:payoo 可以用并运算兼容多个支付渠道
     */
    private Integer offlineFlag;

    /**
     * 0:自然日 1：自然周 2：自然月  3 ：自然年, 自然周月年值每周、月、年的第一天
     */
    private String offlinePeriodType;

    /**
     * 0-99个period_type 
     */
    private String offlinePeriodValue;

    /**
     * 0:自然日 1：自然周 2：自然月  3：自然年
     */
    private String offlinePeriodLayType;

    /**
     * 0-99个period_lay_type 
     */
    private String offlinePeriodLayValue;

    /**
     * 0.00-9999999.99VND * 100
     */
    private Integer offlinePayAmount;

    /**
     * 0-99999 
     */
    private Integer offlinePayRate;

    /**
     * 业务类型 1 打款 2 收款 3 均可以
     */
    private String serviceType;

    /**
     * 通知商户请求url
     */
    private String returnUrl;

    private Date executionTime;

    /**
     * 是否自动提款
     */
    private String autoWithdraw;

    /**
     * 线下支付执行时间
     */
    private Date offlineExecutionTime;

    /**
     * 国际卡 收费
     */
    private Integer onlineIntlPayAmount;

    /**
     * 国际卡 费率
     */
    private Integer onlineIntlPayRate;

    /**
     * 打款
     */
    private Integer transferFlag;

    /**
     * 打款手续费
     */
    private Integer transferAmount;

    /**
     * 打款费率
     */
    private Integer transferRate;

    /**
     * 0:关闭 1:开通
     */
    private Integer virtualCardFlag;

    /**
     * 用户公密
     */
    private String verifyPubKey;

    /**
     * 系统对应公密
     */
    private String signPubKey;

    /**
     * 系统对应私密
     */
    private String signPriKey;

    private String versionSign;

    /**
     * rsa加密字段
     */
    private String businessDataSign;

    /**
     * rsa签名版本
     */
    private String rsaSignVersion;

    /**
     * rsa签名数据
     */
    private String rsaSignData;

    /**
     * 阶梯计费
     */
    private Object tieredFeeRules;

    private static final long serialVersionUID = 1L;
}