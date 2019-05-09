package com.anyway.security.util;

import com.anyway.security.constant.Algorithms;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.tuple.Pair;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA算法
 *
 * @author: wang_hui
 * @date: 2019/5/9 下午5:16
 */
@Slf4j
public class RSAUtils {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 生成秘钥对
     *
     * @return
     */
    public static Pair<String, String> generateKeyPair() {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(Algorithms.RSA);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return Pair.of(Base64.encodeBase64String(publicKey.getEncoded()), Base64.encodeBase64String(privateKey.getEncoded()));
    }

    /**
     * 私钥加密，公钥解密 - 加密
     *
     * @param privateKey 私钥串
     * @param plainText  明文
     * @return 加密后的base64密文
     */
    public static String encryptWithPrivateKey(String privateKey, String plainText) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(Algorithms.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, priKey);
            byte[] encryptBytes = cipher.doFinal(plainText.getBytes(CHARSET));
            return Base64.encodeBase64String(encryptBytes);
        } catch (Exception e) {
            log.info("rsa encrypt with private key error.", e);
        }
        return null;
    }

    /**
     * 私钥加密，公钥解密 - 解密
     *
     * @param publicKey  公钥串
     * @param cipherText 密文
     * @return 解密后的明文
     */
    public static String decryptWithPublicKey(String publicKey, String cipherText) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(Algorithms.RSA);
            cipher.init(Cipher.DECRYPT_MODE, pubKey);
            byte[] decryptBytes = cipher.doFinal(Base64.decodeBase64(cipherText));
            return new String(decryptBytes, CHARSET);
        } catch (Exception e) {
            log.error("rsa decrypt with public key error.", e);
        }
        return null;
    }

    /**
     * 公钥加密，私钥解密 - 加密
     *
     * @param publicKey 公钥串
     * @param plainText 明文
     * @return 加密后的base64密文
     */
    public static String encryptWithPublicKey(String publicKey, String plainText) {
        try {
            PublicKey pubKey = getPublicKey(publicKey);
            Cipher cipher = Cipher.getInstance(Algorithms.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptBytes = cipher.doFinal(plainText.getBytes(CHARSET));
            return Base64.encodeBase64String(encryptBytes);
        } catch (Exception e) {
            log.error("rsa encrypt with public key error.", e);
        }
        return null;
    }

    /**
     * 公钥加密，私钥解密 - 解密
     *
     * @param privateKey 私钥串
     * @param cipherText 密文
     * @return 解密后的明文
     */
    public static String decryptWithPrivateKey(String privateKey, String cipherText) {
        try {
            PrivateKey priKey = getPrivateKey(privateKey);
            Cipher cipher = Cipher.getInstance(Algorithms.RSA);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            byte[] decryptBytes = cipher.doFinal(Base64.decodeBase64(cipherText));
            return new String(decryptBytes, CHARSET);
        } catch (Exception e) {
            log.error("rsa decrypt with private key error.", e);
        }
        return null;
    }

    /**
     * RSA私钥签名
     * todo
     */


    /**
     * 获取私钥
     *
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PrivateKey getPrivateKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance(Algorithms.RSA);
        return keyFactory.generatePublic(keySpec);
    }

}
