package io.dreamstudio.architecture.user.web.advice;

import io.dreamstudio.common.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class WebUncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(WebUncaughtExceptionHandler.class);

    @ExceptionHandler
    public ApiResult unknownException(Exception e) {
        log.error("发生了未知异常", e);
        return ApiResult.systemError();
    }
}
