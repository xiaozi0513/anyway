package com.anyway.security;

import com.anyway.security.util.RSAUtils;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author: wang_hui
 * @date: 2019/5/9 下午5:43
 */
public class RSATest {
    public static void main(String[] args) {
        Pair<String, String> pair = RSAUtils.generateKeyPair();
        System.out.println("public key: " + pair.getLeft());
        System.out.println("private key: " + pair.getRight());
    }
}
