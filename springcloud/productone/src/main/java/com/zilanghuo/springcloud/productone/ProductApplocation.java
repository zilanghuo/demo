package com.zilanghuo.springcloud.productone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by laiwufa on 2019-11-06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApplocation {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplocation.class, args);
    }
}
