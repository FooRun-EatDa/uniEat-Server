package com.foorun.unieat;

import com.foorun.unieat.config.properties.UniEatPigeonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({ UniEatPigeonProperties.class })
public class UniEatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniEatServerApplication.class, args);
    }

}
