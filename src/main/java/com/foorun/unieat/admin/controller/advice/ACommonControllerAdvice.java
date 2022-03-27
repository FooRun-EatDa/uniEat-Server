package com.foorun.unieat.admin.controller.advice;

import com.foorun.unieat.constant.ResponseCode;
import com.foorun.unieat.domain.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ACommonControllerAdvice {
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiResponse<Void>> except(MissingRequestHeaderException e) {
        log.error("Excepted MissingRequestHeaderException ==> {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(ResponseCode.CODE_400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> except(MethodArgumentNotValidException e) {
        log.error("Excepted MethodArgumentNotValidException ==> {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ApiResponse.badRequest(String.format("요청 파라미터 ['%s']가 유효하지 않습니다 ==> %s",
                        e.getFieldError().getField(), e.getFieldError().getDefaultMessage())));
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
