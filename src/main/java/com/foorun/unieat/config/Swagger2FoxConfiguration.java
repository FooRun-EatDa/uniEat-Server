package com.foorun.unieat.config;

import com.foorun.unieat.constant.JwtConstant;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2FoxConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .pathMapping("/")
//                .securitySchemes(Arrays.asList(createTokenParameter(), createRefreshTokenParameter()))
                .securitySchemes(Arrays.asList(createXMemberIdParameter()))
                .securityContexts(Collections.singletonList(securityContext()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.foorun.unieat.controller"))
                .paths(PathSelectors.ant("/**/**"))
                .build()
                .ignoredParameterTypes(AuthenticationPrincipal.class)
                .apiInfo(new ApiInfoBuilder()
                        .title("UniEat API")
                        .version("v1")
                        .build());
    }

    private SecurityScheme createXMemberIdParameter() {
        return new ApiKey(JwtConstant.AUTH_MEMBER_PREFIX, JwtConstant.AUTH_MEMBER_PREFIX, "header");

    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Lists.newArrayList(
                new SecurityReference(JwtConstant.AUTH_MEMBER_PREFIX,authorizationScopes)
        );
        //jwt 토큰용
//        return Lists.newArrayList(
//                new SecurityReference(JwtConstant.HEADER_NAME, authorizationScopes),
//                new SecurityReference(JwtConstant.HEADER_NAME_REFRESH_TOKEN, authorizationScopes));
    }

    private SecurityScheme createTokenParameter() {
        return new ApiKey(JwtConstant.HEADER_NAME, JwtConstant.HEADER_NAME, "header");
    }

    private SecurityScheme createRefreshTokenParameter() {
        return new ApiKey(JwtConstant.HEADER_NAME_REFRESH_TOKEN, JwtConstant.HEADER_NAME_REFRESH_TOKEN, "header");
    }



}

