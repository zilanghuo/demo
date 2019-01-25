package com.zilang.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author laiwufa
 * @date 2019/1/25 0025
 */
@Slf4j
public class TestThread {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        taskExecutor = (ThreadPoolTaskExecutor) ac.getBean("taskExecutor");
    }

    @org.junit.Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("--------");
                }
            });
        }
    }

}
