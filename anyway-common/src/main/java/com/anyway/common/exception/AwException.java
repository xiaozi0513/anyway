package com.anyway.common.exception;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午6:57
 */
public class AwException extends RuntimeException {
    private static final long serialVersionUID = -2878192759059772395L;

    private int code = 500;
    private String msg;

}
