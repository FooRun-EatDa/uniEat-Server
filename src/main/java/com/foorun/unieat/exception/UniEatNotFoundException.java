package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;
import org.springframework.http.HttpStatus;

public class UniEatNotFoundException extends RuntimeException {
    protected final HttpStatus status;
    protected final String message;

    {
        status = ResponseCode.CODE_404.getStatus();
        message = ResponseCode.CODE_404.getMessage();
    }

    public UniEatNotFoundException() {
        super();
    }

    public UniEatNotFoundException(String message) {
        super(message);
    }

    public UniEatNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
