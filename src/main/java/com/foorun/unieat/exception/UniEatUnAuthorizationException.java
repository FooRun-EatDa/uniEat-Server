package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatUnAuthorizationException extends UniEatRuntimeException {
    public UniEatUnAuthorizationException() {
        super(ResponseCode.CODE_401);
    }
}
