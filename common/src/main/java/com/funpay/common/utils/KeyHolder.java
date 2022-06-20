package com.funpay.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 存放加密解密相关的常量
 *
 * @author Leeko
 * @date 2022/2/27
 **/
public class KeyHolder {

    private static final String FILE_PATH = "D:\\mxico\\key\\key.properties";

    /**
     * 存放系统 rsa 加密解密的版本号和公私钥的对应关系
     */

    public static final HashMap<String, String> KEY_MAP = new HashMap<>();


    /****************************** 上面的 keyMap 的 key begin *********************************/
    /**
     * aes 加密的盐的版本号
     */
    public static final String KEY_AES_SECRETKEY_VERSION = "aes_secretkey_version";
    /**
     * aes 加密的对应版本号的盐
     */
    public static String KEY_AES_SECRETKEY_PREFIX = "aes_secretkey_";
    /**
     * 系统 RSA 的盐的版本号
     */
    public static final String KEY_SYS_RSA_SECRETKEY_VERSION = "sys_rsa_secretkey_version";
    /**
     * 系统 RSA 对应版本号的盐
     */
    public static String KEY_SYS_RSA_SECRETKEY_PREFIX = "sys_rsa_secretkey_";
    /**
     * 系统 rsa 加密的版本号
     */
    public static final String KEY_SYS_RSA_EN_KEY_VERSION = "en_key_version";
    /**
     * 系统 rsa 加密的对应版本号的公钥
     */
    public static String KEY_SYS_RSA_EN_PUB_PREFIX = "en_pub_key_";
    /**
     * 系统 rsa 加密的对应版本号的私钥
     */
    public static String KEY_SYS_RSA_EN_PRI_PREFIX = "en_pri_key_";
    /**
     * 系统 rsa 签名的版本号
     */
    public static final String KEY_SYS_RSA_SIGN_KEY_VERSION = "sign_key_version";
    /**
     * 系统 rsa 签名的对应版本号的公钥
     */
    public static String KEY_SYS_RSA_SIGN_PUB_PREFIX = "sign_pub_key_";
    /**
     * 系统 rsa 签名的对应版本号的私钥
     */
    public static String KEY_SYS_RSA_SIGN_PRI_PREFIX = "sign_pri_key_";

    /****************************** 上面的 keyMap 的 key end *********************************/

    static {
        try {
            Properties properties = new Properties();
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
//            String s;
//            StringBuilder builder = new StringBuilder();
//            while ((s = bufferedReader.readLine()) != null) {
//                builder.append(s);
//            }
//            System.out.println(builder.toString());
            properties.load(new BufferedInputStream(new FileInputStream(new File(FILE_PATH))));
            Set<Map.Entry<Object, Object>> entries = properties.entrySet();
            entries.forEach(i -> {
                KEY_MAP.put(i.getKey().toString(), i.getValue().toString());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
