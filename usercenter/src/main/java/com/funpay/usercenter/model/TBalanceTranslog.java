package com.funpay.usercenter.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_balance_translog
 * @author 
 */
@Data
public class TBalanceTranslog implements Serializable {
    private Integer id;

    private Integer merchantId;

    private Integer operType;

    private Integer balance;

    private Integer status;

    private String tradeNo;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}