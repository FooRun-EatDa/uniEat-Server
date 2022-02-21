package com.foorun.unieat.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE('M', "남", "man"),
    FEMALE('F', "여", "woman");

    private final char alias;
    private final String korean;
    private final String english;
}
