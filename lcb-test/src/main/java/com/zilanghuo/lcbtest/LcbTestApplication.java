package com.zilanghuo.lcbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ImportResource(locations = {"classpath:*.xml"})
@EnableAsync
public class LcbTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcbTestApplication.class, args);
    }

}
