package com.funpay.model.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_business_pay_way
 * @author 
 */
@Data
public class TBusinessPayWay implements Serializable {
    private Integer id;

    /**
     * 商家id
     */
    private Integer merchantId;

    /**
     * 业务id
     */
    private Integer businessId;

    /**
     * 支付渠道
     */
    private String payWay;

    /**
     * 状态，可用或不可用，可能还有其他状态，比如冻结业务，不可支付等,如果有并列状态，用位标记
     */
    private Short status;

    /**
     * 写入时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date updateTime;

    /**
     * 版本号，可用于幂等
     */
    private Long version;

    private static final long serialVersionUID = 1L;
}