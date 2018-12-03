package com.zilanghuo.java8.thread.executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque(4), new UserThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executor.execute(() ->
                    logger.info("----"+ finalI));

        }
    }

}

/**
 * 线程工厂名称
 */
class UserThreadFactory implements ThreadFactory {
    private final AtomicInteger nextId = new AtomicInteger(0);
    @Override
    public Thread newThread(Runnable task) {
        String name = "test-" + nextId.incrementAndGet() + Thread.currentThread().getName();
        Thread thread = new Thread(null, task, name);
        return thread;
    }
}