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
        CountDownLatch downLatch = new CountDownLatch(100);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque(200), new UserThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 1; i <= 100; i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                logger.info("----" + finalI);
                downLatch.countDown();
            });

        }
        //测试shutdown，是否等待对了执行完毕
        executor.shutdown();
        try {
            downLatch.await();
        } catch (InterruptedException e) {

        }
        logger.info("执行完毕！");

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