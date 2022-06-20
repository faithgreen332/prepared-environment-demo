package com.funpay.exception.pojo.response;

/**
 * 错误返回结果
 *
 * @author Leeko
 * @date 2022/3/9
 **/

public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {
    }

    public ErrorResponse(int code, String message) {
        super(code, message);
    }
}
