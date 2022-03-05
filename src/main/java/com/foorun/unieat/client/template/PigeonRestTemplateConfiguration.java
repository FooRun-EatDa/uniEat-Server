package com.foorun.unieat.client.template;

import com.foorun.unieat.config.properties.UniEatPigeonProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PigeonRestTemplateConfiguration implements ClientHttpRequestInterceptor {
    private static final String BEAN_NAME = "pigeonRestTemplate";
    private final UniEatPigeonProperties uniEatPigeonProperties;

    @Bean(name = BEAN_NAME)
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(String.format("%s%s",
                        uniEatPigeonProperties.getBaseUrl(),
                        uniEatPigeonProperties.getContextUri()))
                .interceptors(this)
                .build();
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("pigeon api request ==> {}", request.getURI());
        ClientHttpResponse clientHttpResponse = execution.execute(request, body);
        //  TODO : Response Logging
//        log.info("pigeon api response ==> {}", IOUtils.toString(clientHttpResponse.getBody(), StandardCharsets.UTF_8));
        return clientHttpResponse;
    }
}
