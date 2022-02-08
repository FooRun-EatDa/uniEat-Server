package com.foorun.unieat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.foorun.unieat.config.factory.ObjectMapperFactory;

/**
 * 객체와 Json 형식간의 변환 유틸
 */
public final class JsonUtil {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = ObjectMapperFactory.getInstance();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String asJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asPrettyJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonWithoutSpaces(String string) {
        try {
            return objectMapper.readTree(string).toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
