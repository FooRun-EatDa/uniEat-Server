package com.foorun.unieat.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Getter
@ConfigurationProperties("pigeon")
@ConstructorBinding
@RequiredArgsConstructor
@EnableConfigurationProperties({ UniEatPigeonProperties.class })
public class UniEatPigeonProperties {
    private final String baseUrl;
    private final String contextUri;
}
