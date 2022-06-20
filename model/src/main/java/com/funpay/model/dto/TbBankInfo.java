package com.funpay.model.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_bank_info
 * @author 
 */
@Data
public class TbBankInfo implements Serializable {
    private Integer id;

    /**
     * 实际发生交易的银行的唯一标识码
     */
    private String bankCode;

    /**
     * 银行名字
     */
    private String bankName;

    /**
     * 银行状态，1 可用 0 不可用 
     */
    private Boolean status;

    /**
     * 父银行 id
     */
    private Integer pid;

    /**
     * 银行简称
     */
    private String abbr;

    /**
     * 提供给用户的时候告知的支付方式，我们系统自己拟定
     */
    private String payWay;

    /**
     * 单位天，对应payway的押账期（银行结算清楚了，回调我们了，这时候钱物理上已经到我们账上，但是要等我们的财务对账完成，手动确认到账了才是系统认为的到账，这时候才会给客户回款，这期间的时间就是押账期），我们根据银行的流程和我们自己的流程综合考虑设计，写入
     */
    private Integer accountingPeriod;

    /**
     * 不支持线上转入的银行 code，多个用下划线拼接
     */
    private String onlineInUnsupport;

    /**
     * 不支持线上转出的银行 code，多个用下划线拼接
     */
    private String onlineOutUnsupport;

    /**
     * 不支持线下转入的银行 code，多个用下划线拼接
     */
    private String offlineInUnsupport;

    /**
     * 不支持线下转出的银行 code，多个用下划线拼接
     */
    private String offlineOutUnsupport;

    /**
     * 支持的转账类型，用 smallint 类型 8 个 bit 可表示 8 种类型，从低位开始(1表示支持，0表示不支持)，线上转入(0x0001),线上转出(ox0002),线下转入(ox0004),线下转出(ox0008)
     */
    private Short supportEnum;

    /**
     * 入库时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 版本号，可以用于幂等
     */
    private Long version;

    private static final long serialVersionUID = 1L;
}