package com.funpay.usercenter.utils;


import java.util.Date;
import java.util.Random;

/**
 * @author dave
 */
public class TransUtil {
    // 默认buyoo交易
    private final static String BUYOO = "000000";
    private final static String PANDADONG = "001000";
    private final static String BUYOO_VIP = "002000";
    private final static String BUYOO_PAY = "003000";
    private final static String BUYOO_CRAD = "004000";

    private final static String PHP_PAY = "010000";
    private final static String PHP_CARD = "011000";

    public static String getBuyooVip() {
        return BUYOO_VIP;
    }

    public static String getBuyooPay() {
        return BUYOO_PAY;
    }

    public static String getBuyoo() {
        return BUYOO;
    }

    public static String getPandadong() {
        return PANDADONG;
    }

    /**
     * 交易transactionid生成方法
     *
     * @param transType 交易类型
     * @param serverId  服务器编号
     * @return String
     */
    public static String generateTransId(String transType, String serverId) {
        Random random = new Random();
        return BUYOO + transType + serverId + DateUtil.getFormatTime(new Date(), 15) + random.nextInt(10)
                + random.nextInt(10) + random.nextInt(10);
    }

    /**
     * 交易BUYOO_PAY生成方法
     *
     * @param transType 交易类型
     * @param serverId  服务器编号
     * @return String
     */
    public static String getTransId(String transType, String serverId) {
        Random random = new Random();
        return BUYOO_PAY + transType + serverId + DateUtil.getFormatTime(new Date(), 15) + random.nextInt(10)
                + random.nextInt(10) + random.nextInt(10);
    }


    /**
     * 菲律宾交易ID生成方法
     *
     * @param businessCode 交易类型
     * @param serverId     服务器编号
     * @return trans id
     */
    public static String getTransIdPHP(String businessCode, String serverId) {
        Random random = new Random();
        return PHP_PAY + businessCode + serverId + DateUtil.getFormatTime(new Date(), 15) + random.nextInt(10)
                + random.nextInt(10) + random.nextInt(10);
    }

    /**
     * 菲律宾交易ID生成方法
     *
     * @param transType 交易类型
     * @param serverId  服务器编号
     * @return trans id
     */
    public static String getTransIdCardPHP(String transType, String serverId) {
        Random random = new Random();
        return PHP_CARD + transType + serverId + DateUtil.getFormatTime(new Date(), 15) + random.nextInt(10)
                + random.nextInt(10) + random.nextInt(10);
    }


    /**
     * 交易BUYOO_CRAD生成方法
     *
     * @param transType 交易类型
     * @param serverId  服务器编号
     * @return String
     */
    public static String getTransIdCard(String transType, String serverId) {
        Random random = new Random();
        return BUYOO_CRAD + transType + serverId + DateUtil.getFormatTime(new Date(), 15) + random.nextInt(10)
                + random.nextInt(10) + random.nextInt(10);
    }


    private static final Integer count = 10;
    private static final char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9'};

    /**
     * 生成UC的fun id
     *
     * @param countryCode countryCode
     * @return String
     */
    public static String generationFunId(int countryCode) {
        StringBuffer buff = new StringBuffer();
        if (countryCode == 66) {
            buff.append("TH");
        } else if (countryCode == 86) {
            buff.append("CH");
        } else if (countryCode == 62) {
            buff.append("ID");
        } else if (countryCode == 84) {
            buff.append("VN");
        } else {
            buff.append("FM");
        }

        for (int i = 0; i < count; i++) {
            buff.append(codeSequence[new Random().nextInt(36)]);
        }
        return buff.toString();
    }

}
