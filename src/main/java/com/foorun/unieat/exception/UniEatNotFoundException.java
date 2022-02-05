package com.foorun.unieat.exception;

import com.foorun.unieat.constant.HttpStatusMessage;
import org.springframework.http.HttpStatus;

public class UniEatNotFoundException extends RuntimeException {
    protected final HttpStatus status;
    protected final String message;

    {
        status = HttpStatus.NOT_FOUND;
        message = HttpStatusMessage.NOT_FOUND.getMessage();
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
