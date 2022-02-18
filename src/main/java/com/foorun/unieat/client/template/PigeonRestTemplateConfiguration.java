package com.foorun.unieat.client.template;

import com.foorun.unieat.config.properties.UniEatPigeonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class PigeonRestTemplateConfiguration {
    private static final String BEAN_NAME = "pigeonRestTemplate";
    private final UniEatPigeonProperties uniEatPigeonProperties;

    @Bean(name = BEAN_NAME)
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(String.format("%s%s",
                        uniEatPigeonProperties.getBaseUrl(),
                        uniEatPigeonProperties.getContextUri()))
                .build();
    }
}
