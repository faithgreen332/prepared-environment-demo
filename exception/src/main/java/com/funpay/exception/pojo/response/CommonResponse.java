package com.funpay.exception.pojo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通用返回结果
 *
 * @author Leeko
 * @date 2022/3/9
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonResponse<T> extends BaseResponse {

    /**
     * 数据列表
     */
    protected T data;

    public CommonResponse() {

    }

    public CommonResponse(T data) {
        this.data = data;
    }
}
