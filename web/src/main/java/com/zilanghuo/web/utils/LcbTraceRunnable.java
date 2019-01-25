package com.zilanghuo.web.utils;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * @author laiwufa
 * @date 2019/1/25
 */
public abstract class LcbTraceRunnable implements Runnable {

    /**
     * 具体的内部实现
     */
    public abstract void concreteRun();

    @Override
    public void run() {
        MDC.put("traceId", UUID.randomUUID().toString().replaceAll("-", ""));
        concreteRun();
    }
}
