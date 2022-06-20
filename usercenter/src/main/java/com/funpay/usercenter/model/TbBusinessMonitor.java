package com.funpay.usercenter.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * tb_business_monitor
 * @author 
 */
@Data
public class TbBusinessMonitor implements Serializable {
    private Integer id;

    /**
     * 业务系统唯一编号
     */
    private String businessCode;

    /**
     * 0 可用 1 不可用 2 切换其他通道
     */
    private Integer status;

    /**
     * 切换通道业务码
     */
    private String switchCode;

    private String mess;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}