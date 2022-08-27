package com.foorun.unieat.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>,String> {
//    private static final ObjectMapper mapper = new ObjectMapper()
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//            .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
            return attribute == null ? null : String.join(",",attribute);

    }

    @Override
    public List convertToEntityAttribute(String dbData) {
            if(dbData == null)return Collections.emptyList();
            return Arrays.asList(dbData.split(","));

    }
}
