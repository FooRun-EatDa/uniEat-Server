package com.foorun.unieat.domain.common;

import com.fasterxml.jackson.annotation.JsonValue;
import com.foorun.unieat.exception.UniEatLogicalException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public enum PostType {
    BEST,           //  BEST 게시판 게시글
    RESTAURANT,     //  맛집 게시판 게시글
    LIGHTNING;      //  번개 모집 게시판 게시글

    /**
     * BEST 게시판 여부
     * @return 맞는가 ?
     */
    public boolean isBest() {
        return this == BEST;
    }

    public static List<PostType> asList() {
        return Arrays.stream(values())
                .collect(Collectors.toList());
    }

    public static PostType indexOf(int index) {
        try {
            return asList().get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new UniEatLogicalException();
        }
    }

    @JsonValue
    public String getKey() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
