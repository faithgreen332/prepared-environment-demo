package com.funpay.exception.pojo.response;

import com.funpay.exception.constant.IResponseEnum;
import com.funpay.exception.constant.enums.CommonResponseEnum;
import lombok.Data;

/**
 * 基础返回结果
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Data
public class BaseResponse {

    protected int code;
    protected String message;

    /**
     * 默认返回成功
     */
    public BaseResponse() {
        this(CommonResponseEnum.SUCCESS);
    }

    public BaseResponse(IResponseEnum responseEnum) {
        this(responseEnum.getCode(), responseEnum.getMessage());
    }

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
