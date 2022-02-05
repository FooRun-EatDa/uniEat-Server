package com.foorun.unieat.util;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdentifyGenerator {
    private static final String chars;
    private static final Random random;
    private static final int DEFAULT_SIZE = 10;

    static {
        chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        random = new Random();
    }

    /**
     * 랜덤한 Long 타입 정수 생성
     * @param size 길이
     */
    public static long number(int size) {
        return Long.parseLong(IntStream.range(0, size)
                .mapToObj(i -> String.valueOf(random.nextInt(size)))
                .collect(Collectors.joining()));
    }

    public static long number() {
        return number(DEFAULT_SIZE);
    }
}
