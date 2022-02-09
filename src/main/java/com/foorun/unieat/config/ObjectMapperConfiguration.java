package com.foorun.unieat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foorun.unieat.config.factory.ObjectMapperFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ObjectMapperConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.getInstance();
    }
}
