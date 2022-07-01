package com.foorun.unieat.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foorun.unieat.domain.report.dto.Report;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;

@Slf4j
@Getter
public class ReportDataConverter implements AttributeConverter<Report,String> {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }


    @Override
    public String convertToDatabaseColumn(Report attribute) {
        if(attribute == null)return "";
        try{
            return objectMapper.writeValueAsString(attribute);

        }catch (JsonProcessingException e){
            log.error("fail to serialize as object into Json : {}", attribute, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Report convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Report.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }


    }
}
