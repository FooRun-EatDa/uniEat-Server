package com.foorun.unieat.config.properties;

import com.foorun.unieat.domain.JsonSerializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.annotation.PostConstruct;

@Getter
@ConfigurationProperties("pigeon")
@ConstructorBinding
@RequiredArgsConstructor
@ToString
@Slf4j
public class UniEatPigeonProperties implements JsonSerializable {
    private final String baseUrl;
    private final String contextUri;

    @PostConstruct
    protected void postConstruct() {
        log.info("Initialize Properties ==> {}", asJson());
    }
}
