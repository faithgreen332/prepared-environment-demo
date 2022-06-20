package com.funpay.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Status Code
 * <p>
 * Created by dave on 2017/8/22.
 */
@Getter
@AllArgsConstructor
public enum SystemUrl {
    /**
     * 未知类型
     */
    UNKNOW_SYSTEM("UNKNOW_SYSTEM", "unknow type url"),

    /**
     * 支付结果跳转路径
     */
    PAY_RESULT("PAY_RESULT", "pay result url"),

    /**
     * 支付结果跳转路径
     */
    PAY_RESULT_VIP("PAY_RESULT_VIP", "pay vip result url"),

    /**
     * 新用户中心系统地址
     */
    PAY_USERCENTER_SYSTEM("PAY_USERCENTER_SYSTEM", "PAY_USERCENTER_SYSTEM"),

    /**
     * 统一使用key
     */
    INTERNAL_KEY("INTERNAL_KEY", "INTERNAL_KEY"),

    /**
     * 统一内部ip验证 外部服务器连接系统时使用
     */
    INTERNAL_IP_LIMIT("INTERNAL_IP_LIMIT", "INTERNAL_IP_LIMIT"),

    /**
     * 支付系统(数据库标记 变化)
     */
    PAY_SYSTEM("PAY_SYSTEM", "pay system url"),
    ;
    private String name;

    private String value;

    public static SystemUrl getSystemCode(String name) {
        for (SystemUrl systemUrl : SystemUrl.values()) {
            if (systemUrl.getName().equals(name)) {
                return systemUrl;
            }
        }
        return SystemUrl.UNKNOW_SYSTEM;
    }
}
