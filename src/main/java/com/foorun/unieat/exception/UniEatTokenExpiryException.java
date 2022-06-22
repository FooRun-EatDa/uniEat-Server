package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;

public class UniEatTokenExpiryException extends UniEatRuntimeException {
    public UniEatTokenExpiryException() {
        super(ResponseCode.CODE_403);
        this.setMessage("토큰의 유효기간이 만료되었습니다.");
    }
}
