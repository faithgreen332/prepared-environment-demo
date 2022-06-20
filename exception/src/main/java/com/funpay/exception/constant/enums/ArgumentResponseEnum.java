package com.funpay.exception.constant.enums;

import com.funpay.exception.assertion.CommonExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Leeko
 * @date 2022/3/9
 **/
@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements CommonExceptionAssert {

    /**
     * 绑定参数校验异常
     */
    VALID_ERROR(6000, "参数校验异常"),
    ;

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
}
