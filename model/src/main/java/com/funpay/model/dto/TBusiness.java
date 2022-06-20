package com.funpay.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * t_bussiness
 * @author 
 */
@Data
public class TBusiness implements Serializable {
    private Integer id;

    /**
     * 商家id
     */
    private Integer merchantId;

    /**
     * 业务名
     */
    private String businessName;

    /**
     * 业务类型1:游戏 2：应用
     */
    private String businessType;

    /**
     * 1:Androdi 2:iOS 3:Web
     */
    private String businessPlatform;

    /**
     * 计算 vat 的时候用到的固定比例 id，关联表 tb_vat_rate 的 id
     */
    private Integer vatRateId;

    /**
     * 阶梯计费规则的 id，关联表 tb_step_fee 的 id
     */
    private Integer stepFeeId;

    /**
     * 其他计费规则的 id，预留字段
     */
    private Integer thirdFeeId;

    /**
     * 0:正常 2：禁止支付 3：禁止结算 与商家的
     */
    private String status;

    /**
     * 业务 rsa 公钥，由商家在创建业务时提供
     */
    private String businessRsaPubKey;

    /**
     * 通知商户请求url
     */
    private String returnUrl;

    /**
     * 业务的签名，为了防止业务被串改，对业务进行签名，使用前进行验证，注意签名的值不一定只有这个表里的字段，有可能用别的表的字段加入签名里
     */
    private String sign;

    /**
     * 预留字段
     */
    private String field;

    private Date createTime;

    private Date updateTime;

    /**
     * 版本号，可用于幂等
     */
    private Long version;

    private static final long serialVersionUID = 1L;
}