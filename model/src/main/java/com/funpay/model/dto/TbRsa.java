package com.funpay.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_rsa
 *
 * @author
 */
@Data
public class TbRsa implements Serializable {
    private Integer id;

    /**
     * 系统用于加密和签名的文件存放路径
     */
    private String filePath;

    /**
     * 系统用于签名的盐经过rsa加密后的值
     */
    private String signSecretKey;

    /**
     * 系统签名密钥对版本号
     */
    private Long signVersion;

    /**
     * 状态，可用或不可用
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