package com.zilang.web;

import com.zilanghuo.web.AsynThreadService;
import com.zilanghuo.web.utils.TraceThreadPoolTaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.UUID;

/**
 * @author laiwufa
 * @date 2019/1/25 0025
 */
@Slf4j
public class TestThread {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    private TraceThreadPoolTaskExecutor traceTaskExecutor;

    private AsynThreadService asynThreadService;

    @org.junit.Before
    public void init() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        taskExecutor = (ThreadPoolTaskExecutor) ac.getBean("taskExecutor");
        traceTaskExecutor = (TraceThreadPoolTaskExecutor) ac.getBean("traceTaskExecutor");
        asynThreadService = (AsynThreadService) ac.getBean("asynThreadService");
    }

    @org.junit.Test
    public void test() throws Exception {
        for (int i = 0; i < 100; i++) {
            asynThreadService.threadTest();
        }
        Thread.sleep(5000);

     /*
       // 重写xml配置方式
       for (int i = 0; i < 100; i++) {
            traceTaskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("------");
                }
            });*/

          /* 重写runnable方式
          taskExecutor.execute(new TraceRunnable() {
                @Override
                public void concreteRun() {
                    log.info("--------");
                }
            });*/

    }
}
