package com.foorun.unieat.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConfigurationProperties("pigeon")
@ConstructorBinding
@RequiredArgsConstructor
@ToString
public class UniEatPigeonProperties {
    private final String baseUrl;
    private final String contextUri;
}
