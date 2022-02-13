package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatNotFoundException extends UniEatRuntimeException {
    public UniEatNotFoundException() {
        super(ResponseCode.CODE_404);
    }
}
