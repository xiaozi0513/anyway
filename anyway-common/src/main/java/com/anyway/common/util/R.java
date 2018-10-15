package com.anyway.common.util;

import com.anyway.common.constant.CodeEnum;

import java.util.HashMap;

/**
 * 返回数据模型
 *
 * @author: wang_hui
 * @date: 2018/10/9 下午5:58
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = -5323060105516606885L;
    public static final String CODE = "code";
    public static final String MESSAGE = "message";

    public R() {
        put(CODE, CodeEnum.SUCCESS.getCode());
        put(MESSAGE, CodeEnum.SUCCESS.getMessage());
    }

    public static R ok() {
        return new R();
    }

    public static R error() {
        return error(CodeEnum.ERROR.getCode(), CodeEnum.ERROR.getMessage());
    }

    public static R error(String message) {
        return error(CodeEnum.ERROR.getCode(), message);
    }

    public static R error(int code, String message) {
        R r = new R();
        r.put(CODE, code);
        r.put(MESSAGE, message);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
