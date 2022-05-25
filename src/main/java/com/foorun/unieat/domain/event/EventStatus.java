package com.foorun.unieat.domain.event;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventStatus {
    VALID("진행중"),
    EXPIRED("만료됨"),
    NOT_APPLICABLE("해당없음");

    private final String status;

}
