package com.foorun.unieat.controller.advice;

import com.foorun.unieat.constant.ResponseCode;
import com.foorun.unieat.domain.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiResponse<Void>> except(MissingRequestHeaderException e) {
        log.error("Excepted MissingRequestHeaderException ==> {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(ResponseCode.CODE_400));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> except(HttpRequestMethodNotSupportedException e) {
        log.error("Excepted HttpRequestMethodNotSupportedException ==> {}", e.getMethod());
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(ResponseCode.CODE_405));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> except(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(ResponseCode.CODE_500));
    }
}
