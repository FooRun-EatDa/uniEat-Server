package com.foorun.unieat.exception.badrequest;

import com.foorun.unieat.exception.UniEatBadRequestException;

public class UniEatInvalidParameterException extends UniEatBadRequestException {
    private static final String MESSAGE_FORMAT = "요청 파라미터가 유효하지 않습니다 ==> ['%s']";

    public UniEatInvalidParameterException(String parameterName) {
        this.setMessage(String.format(MESSAGE_FORMAT, parameterName));
    }
}
