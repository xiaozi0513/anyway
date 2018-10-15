package com.anyway.common.exception;

import com.anyway.common.util.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: wang_hui
 * @date: 2018/10/10 下午6:14
 */
@RestControllerAdvice
public class CommonExceptionHandler {
    public static final Logger logger = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public R handleThrowable(Throwable e) {
        logger.error(e.getMessage(), e);
        return R.error();
    }

}
