package com.foorun.unieat.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java Reflection 유틸
 */
public final class ReflectionUtil {
    public static List<Field> getAccessibleFields(Object object) {
        return getAccessibleFields(object.getClass());
    }

    public static List<Field> getAccessibleFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toList());
    }
}
