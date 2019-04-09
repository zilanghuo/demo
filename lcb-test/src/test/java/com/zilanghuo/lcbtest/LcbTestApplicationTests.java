package com.zilanghuo.lcbtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ImportResource(locations = {"classpath:*.xml"})
@EnableAsync
public class LcbTestApplicationTests extends BaseSpringBootTest {

    @Test
    public void contextLoads() {
        System.out.println("------");
    }

}
