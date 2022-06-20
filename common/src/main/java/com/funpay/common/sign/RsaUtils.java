package com.funpay.common.sign;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * rsa加密(接口使用)
 *
 * @author dave
 * @date 2021/05/22
 */
@Slf4j
public class RsaUtils {

    // 不仅可以使用DSA算法，同样也可以使用RSA算法做数字签名
    private static final String ALGORITHM = "RSA";
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

    /**
     * 获取私密
     *
     * @param privatePath 私密地址
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKeyFromPem(String privatePath) throws Exception {
        final byte[] privKeyBytes = loadPem(privatePath);
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM).generatePrivate(keySpec);
    }

    /**
     * 获取私密
     *
     * @param privKeyBytes 私密字节
     * @return RSAPrivateKey
     * @throws Exception
     */
    public static RSAPrivateKey getPrivateKey(String privKey) {
        byte[] privKeyBytes = decryptBase64(privKey);
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privKeyBytes);
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(ALGORITHM).generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            log.error("获取私钥错误：" + e.getMessage());
        }
        return null;
    }

    /**
     * 获取公密
     *
     * @param publicPath 公密地址
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey getPublicKeyFromPem(String publicPath) throws Exception {
        final byte[] privKeyBytes = loadPem(publicPath);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privKeyBytes);
        return (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(keySpec);
    }

    /**
     * 获取公密
     *
     * @param privKeyBytes 公密字节
     * @return RSAPublicKey
     * @throws Exception
     */
    public static RSAPublicKey getPublicKey(String privKey) throws Exception {
        byte[] privKeyBytes = decryptBase64(privKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(privKeyBytes);
        return (RSAPublicKey) KeyFactory.getInstance(ALGORITHM).generatePublic(keySpec);
    }

    /**
     * 读取PEM文件
     *
     * @param path 文件路径
     * @return 读取到的字节流
     * @throws IOException 读取异常
     */
    public static byte[] loadPem(String path) throws IOException {
        InputStream in = RsaUtils.class.getResourceAsStream(path);
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        /* 跳过 第一行 */
        br.readLine();
        String s = br.readLine();
        StringBuilder res = new StringBuilder();
        /* 跳过 最后一行 */
        while (s.charAt(0) != '-') {
            res.append(s).append("\r");
            s = br.readLine();
        }
        return decryptBase64(res.toString());
    }

    /**
     * 用私钥对信息进行数字签名
     *
     * @param data 签名数据
     * @return 生成的签名
     * @throws Exception 加密异常
     */
    public static String signFromByte(byte[] data, RSAPrivateKey privateKey) throws Exception {
        // 用私钥对信息进行数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return encryptBASE64(signature.sign());

    }

    /**
     * 用私钥对信息进行数字签名
     *
     * @param data 签名数据
     * @return 生成的签名
     * @throws Exception 加密异常
     */
    public static String signFromStr(String data, RSAPrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(Base64.decodeBase64(data));
            return encryptBASE64(signature.sign());
        } catch (Exception e) {
            log.error("rsa 加密错误：" + e.getMessage());
        }
        return null;
    }

    /**
     * BASE64Encoder 加密
     *
     * @param data 要加密的数据
     * @return 加密后的字符串
     */
    private static String encryptBASE64(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }

    private static byte[] decryptBase64(String data) {
        return Base64.decodeBase64(data);
    }

    /**
     * 验证签名
     *
     * @param data      签名字节
     * @param publicKey 公密
     * @param sign      核验签名
     * @return
     * @throws Exception
     */
    public static boolean verifyWithPublicKey(byte[] data, RSAPublicKey publicKey, String sign) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        // 验证签名
        return signature.verify(decryptBase64(sign));
    }

    /**
     * RSA公钥加密
     *
     * @param str 待加密字符串
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encryptWithPublicKey(String str, String charset, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] data = str.getBytes(charset);
        return encryptBASE64(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data, publicKey.getModulus().bitLength()));
    }

    /**
     * RSA私钥解密
     *
     * @param encryptStr 已加密字符串
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decryptWithPrivateKey(String encryptStr, String charset, RSAPrivateKey privateKey) throws Exception {
        // RSA解密
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // 64位解码加密后的字符串
        byte[] data = decryptBase64(encryptStr);
        data = rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, data, privateKey.getModulus().bitLength());
        return new String(data, charset);
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) throws IOException {
        int maxBlock;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            throw new RuntimeException("加解密阀值为[" + maxBlock + "]的数据时发生异常", e);
        }
        byte[] resultData = out.toByteArray();
        out.close();
        return resultData;
    }
}
