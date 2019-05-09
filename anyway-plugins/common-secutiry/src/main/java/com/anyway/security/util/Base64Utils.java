package com.anyway.security.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Base64编解码
 *
 * @author: wang_hui
 * @date: 2019/5/9 下午3:30
 */
public class Base64Utils {
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * 加密字符串，默认UTF-8编码
     *
     * @param data
     * @return
     */
    public static String encrypt(String data) {
        return new String(encrypt(data.getBytes(CHARSET)), CHARSET);
    }

    /**
     * 加密字符串
     *
     * @param data
     * @param charset 编码
     * @return
     */
    public static String encrypt(String data, Charset charset) {
        return new String(encrypt(data.getBytes(charset)), charset);
    }

    /**
     * 加密
     *
     * @param binaryData
     * @return
     */
    public static byte[] encrypt(byte[] binaryData) {
        return Base64.encodeBase64(binaryData);
    }

    /**
     * 解密字符串，默认UTF-8编码
     *
     * @param base64String
     * @return
     */
    public static String decrypt(String base64String) {
        return new String(decrypt(base64String.getBytes(CHARSET)), CHARSET);
    }

    /**
     * 解密字符串
     *
     * @param base64String
     * @param charset
     * @return
     */
    public static String decrypt(String base64String, Charset charset) {
        return new String(decrypt(base64String.getBytes(charset)), charset);
    }

    /**
     * 解密
     *
     * @param base64Binary
     * @return
     */
    public static byte[] decrypt(byte[] base64Binary) {
        return Base64.decodeBase64(base64Binary);
    }

}
