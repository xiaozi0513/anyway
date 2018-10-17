package com.anyway.common.exception;

/**
 * 平台自定义通用异常
 *
 * @author: wang_hui
 * @date: 2018/10/10 下午6:57
 */
public class AwException extends RuntimeException {
    private static final long serialVersionUID = -2878192759059772395L;

    private int code = 500;
    private String msg;

    public AwException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public AwException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public AwException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public AwException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
