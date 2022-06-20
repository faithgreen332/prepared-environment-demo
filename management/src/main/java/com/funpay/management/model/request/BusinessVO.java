package com.funpay.management.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Leeko
 * @date 2022/2/26
 **/
@Data
public class BusinessVO {

    @NotNull
    private Integer merchantId;
    private String businessName;
    private String businessType;
    private String businessPlatform;
    @NotNull
    private String businessRsaPubKey;
    private String returnUrl;
}
