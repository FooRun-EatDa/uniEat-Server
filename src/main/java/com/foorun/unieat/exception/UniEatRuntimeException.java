package com.foorun.unieat.exception;

import com.foorun.unieat.constant.ResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public abstract class UniEatRuntimeException extends RuntimeException {
    protected final ResponseCode responseCode;
    protected final HttpStatus status;

    @Setter
    protected String message;

    public UniEatRuntimeException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.status = responseCode.getStatus();
        this.message = responseCode.getMessage();
    }
}
