package com.zilanghuo.queue.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author laiwufa
 * @date 2018/12/3
 * use:
 */
@Configuration
@Data
public class ParameterConfig {

    @Value("${rabbitHostName}")
    private String rabbitHostName;

    @Value("${rabbitUserName}")
    private String rabbitUserName;

    @Value("${rabbitPassword}")
    private String rabbitPassword;

    @Value("${rabbitPort}")
    private Integer rabbitPort;

    @Value("${virtualHost}")
    private String virtualHost;

    @PostConstruct
    public void after(){
        System.out.println("----------------after");
    }

}
