package com.foorun.unieat.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HttpStatusMessage {
    /**
     * 404
     */
    NOT_FOUND("리소스가 존재하지 않습니다."),

    /**
     * 403
     */
    FORBIDDEN("인가되지 않은 사용자입니다.");

    private final String message;
}
