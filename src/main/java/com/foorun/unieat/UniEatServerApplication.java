package com.foorun.unieat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniEatServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniEatServerApplication.class, args);
    }

}
