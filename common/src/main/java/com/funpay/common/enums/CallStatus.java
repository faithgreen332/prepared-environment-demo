package com.funpay.common.enums;

/**
 * @author Leeko
 * @date 2022/2/17
 **/
public enum CallStatus {
    /**
     * 调用成功
     */
    SUCCESS(10000, "the request is succeed."),

    /**
     * 调用失败，对方服务错误
     */
    INNER_ERROR(50000, "unknown error.");

    private int code;

    private String value;

    CallStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static CallStatus getStatusCode(int code) {
        for (CallStatus statusCode : CallStatus.values()) {
            if (statusCode.getCode() == code) {
                return statusCode;
            }
        }
        return CallStatus.INNER_ERROR;
    }
}
