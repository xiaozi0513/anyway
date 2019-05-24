package com.anyway.admin.common.constant;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午7:32
 */
public enum CodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "OK"),
    /**
     * 系统异常
     */
    ERROR(500, "系统异常"),
    /**
     * 用户不存在
     */
    USER_NO_EXIST(5001, "用户不存在"),
    /**
     * 密码错误
     */
    PWD_ERROR(5002, "密码错误"),
    /**
     * 用户已存在
     */
    USER_EXIST(5003, "用户已存在");

    private int code;
    private String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
