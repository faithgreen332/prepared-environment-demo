package com.funpay.exception.constant.enums;

import com.funpay.exception.assertion.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用的返回结果
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Getter
@AllArgsConstructor
public enum BusinessResponseEnum implements BusinessExceptionAssert {

    /**
     * 业务异常，可以自定义
     */
    BUSINESS_ERROR(6001, "业务异常");

    private int code;
    private String message;
}
