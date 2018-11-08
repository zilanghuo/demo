package com.zilanghuo.java8.thread.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author laiwufa
 * @date 2018/11/8
 * use: 验证设置用户线程的名称
 */
public class TestExecutors {

    static Logger logger = LoggerFactory.getLogger(TestExecutors.class);

    public static void main(String[] args) {
        executorBeanFactory();
    }

    public static void executorBeanFactory() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue(), new UserThreadFactory());
        for (int i = 0; i < 50; i++) {
            executor.execute(() ->
                    logger.info("----"));

        }
    }

}

/**
 * 线程工厂名称
 */
class UserThreadFactory implements ThreadFactory {
    private final AtomicInteger nextId = new AtomicInteger(1);
    @Override
    public Thread newThread(Runnable task) {
        String name = "test-" + nextId.incrementAndGet() + Thread.currentThread().getName();
        Thread thread = new Thread(null, task, name);
        return thread;
    }
}