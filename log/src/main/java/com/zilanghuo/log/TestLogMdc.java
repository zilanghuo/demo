package com.zilanghuo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2018/11/7
 * use: 测试mdc，是否跟线程池有关;如果线程池为固定线程，不回收，mdc的值是不会更新的
 */
public class TestLogMdc {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger("TestLogMdc");
        // 初始化mdc的值
        MDC.put("key", "value-1");
        logger.info("Hello world.");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        for (int i = 0; i < 10; i++) {
            MDC.put("key", "value-" + i);
            int finalI = i;
            poolExecutor.execute(() -> {
                try {
                    if (finalI < 3) {
                        MDC.put("key", "executor-" + finalI);
                    }
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                }
                logger.info("--");
            });
        }
        // 重置MDC的值
        MDC.put("key", "value-2");
        for (int i = 0; i < 5; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    logger.info("*****");
                }
            });
        }
    }

}
