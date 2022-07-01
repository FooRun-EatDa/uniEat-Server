package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatBlockedUserException extends UniEatRuntimeException{

    public UniEatBlockedUserException() {
        super(ResponseCode.CODE_403);
    }
}
