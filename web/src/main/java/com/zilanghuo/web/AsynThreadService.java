package com.zilanghuo.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author laiwufa
 * @date 2019/1/25 0025 下午 3:32
 */
@Service
@Slf4j
public class AsynThreadService {

    @Async("traceTaskExecutor")
    public void threadTest() {
        log.info("内部实现---------------");
    }

}
