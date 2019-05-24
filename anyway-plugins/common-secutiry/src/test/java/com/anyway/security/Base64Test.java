package com.anyway.security;

import com.anyway.security.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author: wang_hui
 * @date: 2019/5/9 下午3:57
 */
public class Base64Test {

    /**
     * ISO-8859-1不支持中文
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "我的名字交叫Tony";
        System.out.println(Base64Utils.encrypt(str));
        System.out.println(Base64Utils.encryptURLSafe(str));
        System.out.println(Base64Utils.encrypt(str, StandardCharsets.UTF_8));
        System.out.println(Base64Utils.encrypt(str, StandardCharsets.ISO_8859_1));
        System.out.println(new String(Base64Utils.encrypt(str.getBytes("GBK")), "GBK"));

        System.out.println(Base64Utils.decrypt("5oiR55qE5ZCN5a2X5Lqk5Y+rVG9ueQ=="));
        System.out.println(Base64Utils.decrypt("5oiR55qE5ZCN5a2X5Lqk5Y-rVG9ueQ"));
        System.out.println(Base64Utils.decrypt("Pz8/Pz8/VG9ueQ=="));
        System.out.println(Base64Utils.decrypt("Pz8/Pz8/VG9ueQ==", StandardCharsets.ISO_8859_1));
        System.out.println(new String(Base64Utils.decrypt("Pz8/Pz8/VG9ueQ==".getBytes(StandardCharsets.ISO_8859_1)), StandardCharsets.ISO_8859_1));
        System.out.println(Base64Utils.decrypt("ztK1xMP719a9u73QVG9ueQ=="));
        System.out.println(new String(Base64Utils.decrypt("ztK1xMP719a9u73QVG9ueQ==".getBytes("GBK")), "GBK"));

        System.out.println(new String("中国".getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1));

    }
}
