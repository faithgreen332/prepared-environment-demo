package com.funpay.usercenter.utils;
/**
 * 数字常量
 * @author dave
 * @date 2021/05/25
 */
public class NumberContants {

    /**
     * 一天时间对应秒数 即86400
     *
     */
    public static final Long SEC_IN_DAY = 86400L;

    /**
     * 3 分钟锁 (毫秒)
     *
     */
    public static final int LOCK_FOR_3_MINUTES = 180000;

    /**
     * 10分钟锁 (毫秒)
     *
     */
    public static final int LOCK_FOR_10_MINUTES = 600000;

    /**
     * 10分钟锁 (秒)
     *
     */
    public static final int LOCK_FOR_10_MINUTES_SEC = 1800;

    /**
     * 一天时间对应小时数 即24
     *
     */
    public static final Integer HOUR_IN_DAY = 24;

    /**
     * 日限制 值
     */
    public static final Long TRANSFER_DAY_LIMIT = 5000000L;

    public static final int zero = 0;

    public static final long zero_long = 0L;

    /**
     * 重试次数 - 余额操作
     */
    public static final int COUNT_RETRY = 50;

    /**
     * 递归起始计数
     */
    public static final int COUNT_START = 0;

}
