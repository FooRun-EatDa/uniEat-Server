package com.foorun.unieat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    CODE_200(200, HttpStatus.OK, "정상적으로 처리되었습니다."),
    CODE_201(201, HttpStatus.OK,  "정상적으로 처리되었습니다."),
    CODE_400(400, HttpStatus.BAD_REQUEST,  "요청 양식이 올바르지 않습니다."),
    CODE_401(401, HttpStatus.UNAUTHORIZED,  "인증 정보가 누락되었습니다."),
    CODE_403(403, HttpStatus.FORBIDDEN,  "인가되지 않은 사용자입니다."),
    CODE_404(404, HttpStatus.NOT_FOUND,  "리소스가 존재하지 않습니다."),
    CODE_405(405, HttpStatus.METHOD_NOT_ALLOWED,  "허용되지 않은 요청 방식입니다."),
    CODE_500(500, HttpStatus.INTERNAL_SERVER_ERROR,  "서버에서 요청을 처리 중 오류가 발생했습니다.");

    private final int code;
    private final HttpStatus status;
    private final String message;

    public static ResponseCode findByHttpStatus(HttpStatus httpStatus) {
        return Arrays.stream(values())
                .filter(responseCode -> responseCode.getStatus() == httpStatus)
                .findFirst()
                .orElse(ResponseCode.CODE_500);
    }
}
