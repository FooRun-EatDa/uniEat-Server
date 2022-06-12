package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatLogicalException extends UniEatRuntimeException {
    public UniEatLogicalException() {
        super(ResponseCode.CODE_500);
    }

    public UniEatLogicalException(String message) {
        super(ResponseCode.CODE_500);
        this.setMessage(message);
    }
}
