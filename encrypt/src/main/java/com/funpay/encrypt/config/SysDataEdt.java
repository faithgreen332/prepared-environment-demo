package com.funpay.encrypt.config;

import com.funpay.common.springutil.SpringContextInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 系统签名数据相关
 *
 * @author Leeko
 * @date 2022/2/16
 **/
@Slf4j
public class SysDataEdt implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认路径地址
     */
    private static final String CONFIG_FILE_PATH = "E:\\sys\\";

    /**
     * 默认第一次创造
     */
    private static volatile boolean isCreate = false;

    /**
     * aes 初始化版本
     */
    private static String encryptKeyAes = "encryptKeyAesBalance";

    /**
     * rsa加密 初始化版本
     */
    private static String sysKeyRasPublic = "sysKeyRasPublic";

    /**
     * rsa签名 初始化版本
     */
    private static String rsaSignKeyMapVersion = "rsaSignKeyMapVersion";


    public String getEncryptKeyAes() {
        log.info("getEncryptKeyAes version:" + encryptKeyAes);
        return encryptKeyAes;
    }

    public void setEncryptKeyAes(String encryptKeyAes) {
        if (!StringUtils.isEmpty(encryptKeyAes)) {
            log.info("setEncryptKeyAes version:" + encryptKeyAes);
            SysDataEdt.encryptKeyAes = encryptKeyAes;
        } else {
            throw new RuntimeException("setEncryptKeyAes version fail");
        }
    }

    public String getSysKeyRasPublic() {
        log.info("sysKeyRasPublic version:" + sysKeyRasPublic);
        return sysKeyRasPublic;
    }

    public void setSysKeyRasPublic(String sysKeyRasPublic) {
        if (!StringUtils.isEmpty(sysKeyRasPublic)) {
            log.info("setSysKeyRasPublic version:" + sysKeyRasPublic);
            SysDataEdt.sysKeyRasPublic = sysKeyRasPublic;
        } else {
            throw new RuntimeException("setSysKeyRasPublic version fail");
        }
    }

    public String getRsaSignKeyMapVersion() {
        log.info("getRsaSignKeyMapVersion version:" + rsaSignKeyMapVersion);
        return rsaSignKeyMapVersion;
    }

    public void setRsaSignKeyMapVersion(String rsaSignKeyMapVersion) {
        if (!StringUtils.isEmpty(sysKeyRasPublic)) {
            log.info("setRsaSignKeyMapVersion version:" + rsaSignKeyMapVersion);
            SysDataEdt.rsaSignKeyMapVersion = rsaSignKeyMapVersion;
        } else {
            throw new RuntimeException("setRsaSignKeyMapVersion version fail");
        }
    }

    /**
     * 解密签名集合
     */
    private static Map<String, String> decryptMap;

    /**
     * 加密集合
     */
    private static Map<String, String> encryptMap;

    private volatile static SysDataEdt sysDataEde;

    public static SysDataEdt getInstance() {
        if (sysDataEde == null) {
            synchronized (SysDataEdt.class) {
                if (sysDataEde == null) {
                    sysDataEde = new SysDataEdt();
                }
            }
        }
        return sysDataEde;
    }

    /**
     * 私有构造
     */
    @SuppressWarnings("static-access")
    private SysDataEdt() {
        if (isCreate) {
            throw new RuntimeException("已然被实例化一次，不能在实例化");
        }
        decryptMap = new HashMap<>(20);
        encryptMap = new HashMap<>(10);
        isCreate = true;

        // 读取文件并存储
        InputStream inDecrypt = null;
        InputStream inEncrypt = null;
        Properties decryptPro = new Properties();
        Properties encryptPro = new Properties();
        try {
            /**
             * 判断配置文件是否存在下载地址, 没有则使用默认值
//             */
//            ServerConfig funpayConfig = SpringContextInstance.getBean(ServerConfig.class);
//            String profile = funpayConfig.getProfile();
//            if (!StringUtils.isEmpty(profile)) {
//                inDecrypt = new BufferedInputStream(new FileInputStream(profile + "decrypt.properties"));
//                inEncrypt = new BufferedInputStream(new FileInputStream(profile + "encrypt.properties"));
//            } else {
//                inDecrypt = new BufferedInputStream(new FileInputStream(CONFIG_FILE_PATH + "decrypt.properties"));
//                inEncrypt = new BufferedInputStream(new FileInputStream(CONFIG_FILE_PATH + "encrypt.properties"));
//            }
            decryptPro.load(inDecrypt);
            encryptPro.load(inEncrypt);
        } catch (Exception e) {
            throw new RuntimeException("文件读取失败");
        }
        Set<Map.Entry<Object, Object>> entrySetdecryptPro = decryptPro.entrySet();
        for (Map.Entry<Object, Object> entry : entrySetdecryptPro) {
            setDecryptMap((String) entry.getKey(), (String) entry.getValue());
        }
        Set<Map.Entry<Object, Object>> entrySetencryptPro = encryptPro.entrySet();
        for (Map.Entry<Object, Object> entry : entrySetencryptPro) {
            setEncryptMap((String) entry.getKey(), (String) entry.getValue());
        }

        /**
         * 设置固定值
         * 初始化版本
         */
        setEncryptKeyAes(getEncryptMap("encryptKeyAes"));
        setSysKeyRasPublic(getEncryptMap("sysKeyRasPublic"));
        setRsaSignKeyMapVersion(getEncryptMap("rsaSignKeyMapVersion"));
    }

    /**
     * 获取解密缓存
     *
     * @param key
     * @return
     */
    public String getDecryptMap(String key) {
        return decryptMap.get(key);
    }

    /**
     * 添加缓存
     *
     * @param key
     * @return
     */
    public String setDecryptMap(String key, String value) {
        return decryptMap.put(key, value);
    }

    /**
     * 删除指定key
     *
     * @param key
     * @return
     */
    public String removeDecryptMap(String key) {
        return decryptMap.remove(key);
    }

    /**
     * 获取加密缓存
     *
     * @param key
     * @return
     */
    public String getEncryptMap(String key) {
        return encryptMap.get(key);
    }

    /**
     * 替换加密缓存
     *
     * @param key
     * @return
     */
    public String setEncryptMap(String key, String value) {
        return encryptMap.put(key, value);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return sysDataEde;
    }
}
