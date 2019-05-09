package com.anyway.security.util;

import com.anyway.security.constant.Algorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5签名算法
 *
 * @author: wang_hui
 * @date: 2019/5/9 下午4:15
 */
public class MD5Utils {

    public static String encrypt(String data) {
        byte[] hash = encrypt(data.getBytes());
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toLowerCase();
    }

    public static byte[] encrypt(byte[] binaryData) {
        try {
            MessageDigest digest = MessageDigest.getInstance(Algorithms.MD5);
            digest.update(binaryData);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        }
    }

}
