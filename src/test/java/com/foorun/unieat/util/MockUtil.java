package com.foorun.unieat.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.foorun.unieat.util.ReflectionUtil.getAccessibleFields;

/**
 * Mock Instance 생성
 * 객체의 필드 값은 타입에 따라서 기본값으로 지정
 */
public class MockUtil {
    public static <T> T createMock(Class<T> type) {
        try {
            final T object = type.getDeclaredConstructor().newInstance();
            List<Field> fields = getAccessibleFields(object);
            fields.forEach(field -> setMockValue(object, field));
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> void setMockValue(T object, Field field) {
        try {
            if (field.get(object) != null) {
                return;
            }
            if (isBoolean(field)) {
                field.set(object, true);
            } else if (isNumber(field)) {
                field.set(object, 0L);
            } else if (isCharacter(field)) {
                field.setChar(object, '-');
            } else if (isString(field)) {
                field.set(object, field.getName());
            } else if (field.getType() == LocalDate.class) {
                field.set(object, LocalDate.now());
            } else if (field.getType() == LocalDateTime.class) {
                field.set(object, LocalDateTime.now());
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static boolean isString(Field field) {
        return field.getType() == String.class;
    }

    private static boolean isNumber(Field field) {
        List<?> numberWrapperList = Arrays.asList(Byte.class, Double.class, Float.class, Integer.class, Long.class, Short.class);
        return field.getType().isPrimitive() || numberWrapperList.contains(field.getType());
    }

    private static boolean isCharacter(Field field) {
        return field.getType() == Character.class || field.getType() == char.class;
    }

    private static boolean isBoolean(Field field) {
        return field.getType() == Boolean.class || field.getType() == boolean.class;
    }
}
