package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatBadRequestException extends UniEatRuntimeException {
    public UniEatBadRequestException() {
        super(ResponseCode.CODE_400);
    }

    public UniEatBadRequestException(ResponseCode r){
        super(r);
    }
}
