package com.foorun.unieat.controller.advice;

import com.foorun.unieat.domain.common.api.ApiResponse;
import com.foorun.unieat.exception.UniEatRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.foorun.unieat.controller")
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class UniEatControllerAdvice {
    @ExceptionHandler(UniEatRuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> except(UniEatRuntimeException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ApiResponse.error(e.getResponseCode()));
    }
}
