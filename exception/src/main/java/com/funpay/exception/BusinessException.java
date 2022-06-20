package com.funpay.exception;

import com.funpay.exception.constant.IResponseEnum;

/**
 * 业务异常
 * 业务处理异常时，抛出此异常
 *
 * @author Leeko
 * @date 2022/3/8
 **/
public class BusinessException extends BaseException {
    public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BusinessException(IResponseEnum responseEnum, Object[] args, String massage, Throwable cause) {
        super(responseEnum, args, massage, cause);
    }
}
