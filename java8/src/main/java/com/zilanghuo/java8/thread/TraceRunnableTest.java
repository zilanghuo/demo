package com.zilanghuo.java8.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author laiwufa
 * @date 2019/1/23 0023
 */
public class TraceRunnableTest {

    public static void main(String[] args) {

            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque(4));

        for (int i = 0; i < 100; i++) {
            executor.execute(new TraceRunnable() {
                @Override
                public void trace() {
                    System.out.println("具体实现");
                }
            });
        }
    }
}


abstract class TraceRunnable implements Runnable {

    public abstract void trace();

    @Override
    public void run() {
        System.out.println("start run");
        trace();
    }
}