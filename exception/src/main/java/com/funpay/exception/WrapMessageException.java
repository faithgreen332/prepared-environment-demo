package com.funpay.exception;

/**
 * 只包装了错误信息的 {@link RuntimeException}
 * 用于 {@link com.funpay.exception.assertion.Assert} 种用于包装自定义异常信息
 *
 * @author Leeko
 * @date 2022/3/8
 **/
public class WrapMessageException extends RuntimeException {

    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
