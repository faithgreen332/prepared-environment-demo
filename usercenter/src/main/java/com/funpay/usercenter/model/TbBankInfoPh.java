package com.funpay.usercenter.model;

import lombok.Data;

import java.io.Serializable;

/**
 * tb_bank_info_ph
 *
 * @author
 */
@Data
public class TbBankInfoPh implements Serializable {
    private Integer id;

    /**
     * 银行代码-v5侧
     */
    private String bankCode;

    /**
     * 银行名称
     */
    private String bankName;

    private String brstn;

    /**
     * 产品类型 银行实时/银行非实时
     */
    private String productType;

    private String category;

    private Integer pid;

    private Integer status;

    private Integer version;

    private static final long serialVersionUID = 1L;
}