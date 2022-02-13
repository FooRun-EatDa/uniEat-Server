package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatForbiddenException extends UniEatRuntimeException {
    public UniEatForbiddenException() {
        super(ResponseCode.CODE_403);
    }
}
