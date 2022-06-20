package com.funpay.encrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.math.BigDecimal;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Leeko
 * @date 2022/2/24
 **/
@Slf4j
public class AESUtil {

    private static final String SECRET_KEY = "O37Uh0F7COnSiCmQ";

    public static AtomicReference<String> Atomic_secret_key = new AtomicReference<>();

    private static Cipher cipher = null;

    static {
        try {
            cipher = Cipher.getInstance("AES");

            System.out.println("GetAndSet 之前:" + Atomic_secret_key.get());
            if (Atomic_secret_key.get() == null) {
                Atomic_secret_key.set(SECRET_KEY);
            }
            System.out.println("getAndSet 之后:" + Atomic_secret_key.get());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    /**
     * aes 加密
     *
     * @param sSrc source
     * @param sKey secretKey
     * @return 加密后密文
     */
    public static String en(String sSrc, String sKey) {
        byte[] encrypted = new byte[0];
        try {
            SecureRandom sr = new SecureRandom();
            Key secureKey = getKey(sKey);
            cipher.init(Cipher.ENCRYPT_MODE, secureKey, sr);
            encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        } catch (Exception e) {
            log.error("aes 加密错误：" + e.getMessage());
        }
        return new Base64().encodeToString(encrypted);
    }

    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * aes 解密
     *
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String de(String sSrc, String sKey) {
        String originalString = null;
        try {
            SecureRandom sr = new SecureRandom();
            Key secureKey = getKey(sKey);
            cipher.init(Cipher.DECRYPT_MODE, secureKey, sr);

            byte[] encrypted1 = new Base64().decode(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            originalString = new String(original, "utf-8");
        } catch (Exception ex) {
            log.error("aes 解密出错：" + ex.getMessage());
        }
        return originalString;
    }

    public static Key getKey(String strKey) {
        Key key = null;
        try {
            if (strKey == null) {
                strKey = "";
            }
            KeyGenerator _generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            _generator.init(128, secureRandom);
            key = _generator.generateKey();
        } catch (Exception e) {
            log.error("初始化密钥错误：" + e.getMessage());
        }
        return key;
    }

    public static void main(String[] args) throws Exception {

//        System.out.println(createRandomCharData(16));
//        BigDecimal bigDecimal = new BigDecimal("1058965420.23");
//        String str = bigDecimal.toEngineeringString();
//        String str1 = bigDecimal.toPlainString();
//        System.out.println(str);
//        System.out.println(str1);
//        String str2 = bigDecimal.toString();
//        System.out.println(str2);
//        long begin = System.currentTimeMillis();
//        String s = stringToHexString(en(str2, SECRET_KEY));
//
//        long end = System.currentTimeMillis();
//        System.out.println(s);
//        System.out.println(end - begin);

//        System.out.println(de("1zru77Gvj8t6Oy1Qrrp8rA==", SECRET_KEY));
//        System.out.println(System.currentTimeMillis());
        System.out.println(createRandomCharData(32));

    }

    public static String createRandomCharData(int length) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        Random randdata = new Random();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(3);
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;
                    sb.append((char) data);
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}
