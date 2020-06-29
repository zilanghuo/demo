package com.zilanghuo.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
      //  System.setProperty("java.security.auth.login.config", "/Users/admin/Documents/officeFile/kafka/kafka_client_jaas.conf");
      //  System.setProperty("java.security.krb5.conf", "/Users/admin/Documents/officeFile/kafka/krb5.conf");
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
