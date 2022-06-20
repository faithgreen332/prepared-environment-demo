package com.funpay.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_step_fee
 *
 * @author
 */
@Data
public class TbStepFee implements Serializable {
    private Integer id;

    /**
     * 描述
     */
    private String description;

    /**
     * 值
     */
    private Object value;

    /**
     * 状态，可用或不可用
     */
    private Byte status;

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



