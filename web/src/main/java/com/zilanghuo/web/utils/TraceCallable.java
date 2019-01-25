package com.zilanghuo.web.utils;

import org.slf4j.MDC;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author laiwufa
 * @date 2019/1/25
 */
public abstract class TraceCallable<V> implements Callable<V> {

    /**
     * 具体实现
     * @return
     */
    public abstract V concreteCall() throws Exception;

    @Override
    public V call() throws Exception {
        MDC.put("traceId", UUID.randomUUID().toString().replaceAll("-", ""));
        return concreteCall();
    }
}
