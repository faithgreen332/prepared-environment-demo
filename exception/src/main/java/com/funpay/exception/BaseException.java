package com.funpay.exception;

import com.funpay.exception.constant.IResponseEnum;
import lombok.Getter;

/**
 * 基础类异常，所有自定义的异常类都需要继承本类
 *
 * @author Leeko
 * @date 2022/3/8
 **/
@Getter
public class BaseException extends RuntimeException {

    /**
     * 返回码
     */
    protected IResponseEnum responseEnum;

    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(IResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public BaseException(int code, String message) {
        super(message);
        this.responseEnum = new IResponseEnum() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String message) {
        super(message);
        this.responseEnum = responseEnum;
        this.args = args;
    }

    public BaseException(IResponseEnum responseEnum, Object[] args, String massage, Throwable cause) {
        super(massage, cause);
        this.responseEnum = responseEnum;
        this.args = args;
    }

}
