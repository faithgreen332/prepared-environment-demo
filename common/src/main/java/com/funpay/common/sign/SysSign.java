package com.funpay.common.sign;

import com.funpay.common.constants.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;

/**
 * @author dave
 * @version 1.0 系统加密算法认证
 * @date 2021/05/28
 */
@Slf4j
public class SysSign {

    /**
     * 对内加密 无空值
     *
     * @param key     rsa密钥
     * @param signKey INTERNAL_KEY
     */
    public static String apiSignInternal(String key, String signKey, Object bean) {
        Map<String, String> result;
        try {
            result = JavaBean.convertBeanToMap(bean, false);
            result.put("key", signKey);
            return SysSign.getApiSignInternal(result, RsaUtils.getPrivateKey(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.EMPTY;
    }

    /**
     * 对外api核验算法 空值参与加密
     *
     * @param param
     * @return
     */
    public static boolean checkSign(Map<String, String> param, RSAPublicKey publicKey) {
        String sign = param.get("sign");
        String key = param.get("key");
        param.remove("sign");
        param.remove("key");
        return checkSignApi(param, key, publicKey, true, sign);
    }

    /**
     * 内部api核验算法 空值不参与加密
     *
     * @param param
     * @return
     */
    public static boolean checkSignInternal(Map<String, String> param, RSAPublicKey publicKey) {
        String sign = param.get("sign");
        String key = param.get("key");
        param.remove("sign");
        param.remove("key");
        return checkSignApi(param, key, publicKey, false, sign);
    }

    /**
     * 对外api加密算法
     *
     * @param param
     * @return
     * @throws Exception
     */
    public static String getApiSign(Map<String, String> param, RSAPrivateKey privateKey) throws Exception {
        String key = param.get("key");
        param.remove("sign");
        param.remove("key");
        return getSignApiStr(param, key, privateKey, true);
    }

    /**
     * 内部api加密算法 空值不参与加密
     *
     * @param param
     * @return
     * @throws Exception
     */
    public static String getApiSignInternal(Map<String, String> param, RSAPrivateKey privateKey) throws Exception {
        String key = param.get("key");
        param.remove("sign");
        param.remove("key");
        return getSignApiStr(param, key, privateKey, false);
    }


    /**
     * 字符串排序算法 返回加密前明文
     *
     * @param param
     * @return
     */
    public static String getSignStr(Map<String, String> param, boolean isNull) {
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(param.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : infoIds) {
            // 排除掉不加密的字段
            if (!StringUtils.isEmpty(item.getKey())) {
                String key = item.getKey();
                String val = item.getValue();
                if (StringUtils.isEmpty(val)) {
                    if (isNull) {
                        val = StringUtils.isEmpty(val) ? Constant.EMPTY : val;
                        sb.append(key).append(Constant.EQUAL).append(val).append(Constant.AMPERSAND);
                    }
                } else {
                    sb.append(key).append(Constant.EQUAL).append(val).append(Constant.AMPERSAND);
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 加密算法
     *
     * @param param      加密数据
     * @param privateKey 加密密钥
     * @param isNull     是否可为空
     * @param sign       验证签名
     * @return
     * @throws Exception
     */
    private static String getSignApiStr(Map<String, String> param, String key, RSAPrivateKey privateKey, boolean isNull) throws Exception {
        key = StringUtils.isEmpty(key) ? Constant.EMPTY : key;
        String signStr = getSignStr(param, isNull) + key;
        return RsaUtils.signFromStr(signStr, privateKey);
    }

    /**
     * 核验算法
     *
     * @param param     加密数据
     * @param publicKey 核验公钥
     * @param isNull    是否可为空
     * @param sign      验证签名
     * @return
     */
    private static boolean checkSignApi(Map<String, String> param, String key, RSAPublicKey publicKey, boolean isNull, String sign) {
        try {
            key = StringUtils.isEmpty(key) ? Constant.EMPTY : key;
            String signStr = getSignStr(param, isNull) + key;
            boolean verifyWithPublicKey = RsaUtils.verifyWithPublicKey(signStr.getBytes(), publicKey, sign);
            if (!verifyWithPublicKey) {
                log.info("加密错误:" + signStr);
            }
            return verifyWithPublicKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
