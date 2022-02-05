package com.foorun.unieat.exception;

import com.foorun.unieat.constant.HttpStatusMessage;
import org.springframework.http.HttpStatus;

public class UniEatForbiddenException extends RuntimeException {
    protected final HttpStatus status;
    protected final String message;

    {
        status = HttpStatus.FORBIDDEN;
        message = HttpStatusMessage.FORBIDDEN.getMessage();
    }

    public UniEatForbiddenException() {
        super();
    }

    public UniEatForbiddenException(String message) {
        super(message);
    }

    public UniEatForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
