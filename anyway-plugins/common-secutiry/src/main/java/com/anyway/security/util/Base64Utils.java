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

    public static String encrypt(String data) {
        return new String(encrypt(data.getBytes(CHARSET)), CHARSET);
    }

    public static String encrypt(String data, Charset charset) {
        return new String(encrypt(data.getBytes(charset)), charset);
    }

    public static byte[] encrypt(byte[] binaryData) {
        return Base64.encodeBase64(binaryData);
    }

    public static String encryptURLSafe(String data) {
        return new String(encryptURLSafe(data.getBytes(CHARSET)), CHARSET);
    }

    public static String encryptURLSafe(String data, Charset charset) {
        return new String(encryptURLSafe(data.getBytes(charset)), charset);
    }

    public static byte[] encryptURLSafe(byte[] binaryData) {
        return Base64.encodeBase64URLSafe(binaryData);
    }

    public static String decrypt(String base64String) {
        return new String(decrypt(base64String.getBytes(CHARSET)), CHARSET);
    }

    public static String decrypt(String base64String, Charset charset) {
        return new String(decrypt(base64String.getBytes(charset)), charset);
    }

    public static byte[] decrypt(byte[] base64Binary) {
        return Base64.decodeBase64(base64Binary);
    }

}
