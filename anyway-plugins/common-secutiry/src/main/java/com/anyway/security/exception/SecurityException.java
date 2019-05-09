package com.anyway.security.exception;

/**
 * @author: wang_hui
 * @date: 2019/5/9 下午4:29
 */
public class SecurityException extends RuntimeException {
    private static final long serialVersionUID = -5102930743341252104L;

    public SecurityException() {
        super();
    }

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(Throwable throwable) {
        super(throwable);
    }

    public SecurityException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
