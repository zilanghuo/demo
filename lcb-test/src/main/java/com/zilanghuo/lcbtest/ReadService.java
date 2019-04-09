package com.zilanghuo.lcbtest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author laiwufa
 * @date 2019/4/9 0009 上午 9:52
 */
@Service
@Slf4j
public class ReadService {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Async("taskExecutor")
    public void read() {
        log.info("read------");
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行结束");
    }

    @Autowired
    public void write() {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("write------");
                try {
                    Thread.sleep(2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
