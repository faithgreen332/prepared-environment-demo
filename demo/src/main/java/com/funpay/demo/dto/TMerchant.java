package com.funpay.demo.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

/**
 * t_merchant
 *
 * @author
 */
@Data
public class TMerchant implements Serializable {
    private Integer id;

    /**
     * 商家名字
     */
    private String name;

    /**
     * 简称
     */
    private String abbr;

    /**
     * 描述
     */
    private String description;

    /**
     * 电话
     */
    private String phone;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 开发人员邮箱
     */
    private String developerMail;

    /**
     * 财务邮箱
     */
    private String financeMail;

    /**
     * 预留字段，应该是想用来标记他们的应用
     */
    private String applicationId;

    /**
     * 状态，可能不只可用不可用两种，如果有其他状态，就用位标记
     */
    private Short status;

    /**
     * 从上一状态恢复的原来状态的时间
     */
    private Date recoveryTime;

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