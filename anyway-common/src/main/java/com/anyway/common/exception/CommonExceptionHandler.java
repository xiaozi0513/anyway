package com.anyway.common.exception;

import com.anyway.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author: wang_hui
 * @date: 2018/10/10 下午6:14
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(AwException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public R handleAwException(AwException e) {
        logger.error(e.getMsg(), e);
        return R.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleThrowable(Throwable e) {
        logger.error(e.getMessage(), e);
        return null;
    }

}
