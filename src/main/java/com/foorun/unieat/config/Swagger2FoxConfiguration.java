package com.foorun.unieat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2FoxConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.foorun.unieat.controller"))
                .paths(PathSelectors.ant("/**/**"))
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("UniEat API")
                        .version("v1")
                        .build());
    }
}

