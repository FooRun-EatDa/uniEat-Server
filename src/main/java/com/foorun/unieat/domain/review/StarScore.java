package com.foorun.unieat.domain.review;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StarScore {
    ZERO(0L,"아쉽"),
    ONE(1L,"엥간"),
    TWO(2L,"꿀맛");

    private final Long score;
    private final String name;
}
