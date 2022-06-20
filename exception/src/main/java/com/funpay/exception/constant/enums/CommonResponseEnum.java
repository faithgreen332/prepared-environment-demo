package com.funpay.exception.constant.enums;

import com.funpay.exception.BaseException;
import com.funpay.exception.assertion.CommonExceptionAssert;
import com.funpay.exception.pojo.response.BaseResponse;
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
public enum CommonResponseEnum implements CommonExceptionAssert {

    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),

    SERVER_BUSY(9998, "服务器繁忙"),

    /**
     * 服务异常，无法识别的异常
     */
    SERVER_ERROR(9999, "服务器异常"),

    DATE_NOT_NULL(5001, "日期不能为空"),

    DATETIME_NOT_NULL(5001, "时间不能为空"),

    TIME_NOT_NULL(5001, "时间不能为空"),

    DATE_PATTERN_MISMATCH(5002, "日期[%s]与格式[%s]不匹配，无法解析"),

    PATTERN_NOT_NULL(5003, "日期格式不能为空"),

    PATTERN_INVALID(5003, "日期格式[%s]无法识别");

    private int code;
    private String message;

    /**
     * 校验返回结果是否成功
     *
     * @param response 远程调用的相应
     */
    public static void assertSuccess(BaseResponse response) {
        SERVER_ERROR.assertNotNull(response);
        int code = response.getCode();
        if (CommonResponseEnum.SUCCESS.getCode() != code) {
            throw new BaseException(code, response.getMessage());
        }
    }

}
