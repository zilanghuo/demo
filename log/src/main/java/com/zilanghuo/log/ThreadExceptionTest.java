package com.zilanghuo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2019/4/9 0009 下午 5:13
 */
public class ThreadExceptionTest {


    static Logger log = LoggerFactory.getLogger("ThreadExceptionTest");

    public static void main(String[] args) throws InterruptedException {

        ExecutorService execute = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        execute.execute(new Runnable() {
            @Override
            public void run() {
                log.info("=====11=======");
            }
        });

        TimeUnit.SECONDS.sleep(1);
        execute.execute(new Run1());
        //TimeUnit.SECONDS.sleep(5);
        //execute.execute(new Run2());
        //execute.shutdown();
    }


    private static class Run1 implements Runnable {

        @Override
        public void run() {
            int count = 0;
            while (true) {
                count++;
                log.info("-------222-------------" + count);

                if (count == 10) {
                    try {
                        System.out.println(1 / 0);
                    } catch (Exception e) {
                        log.error("error", e);
                    }
                }

                if (count == 20) {
                    log.info("count=" + count);
                    break;
                }
            }
        }
    }

    private static class Run2 implements Runnable {

        public Run2() {
            log.info("run2 构造函数");
        }

        @Override
        public void run() {
            log.info("run222222222");
        }
    }
}
