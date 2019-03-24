package com.zilanghuo.java8.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadAndCountDownLatch {

    public static void main(String[] args) {
        // 设置的令牌数
        int size = 100;
        CountDownLatch downLatch = new CountDownLatch(size);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque());
        for (int i = 1; i <= size; i++) {
            // 控制并发数
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 具体业务
                        System.out.println("todo------");
                    } finally {
                        // 减少一个令牌
                        downLatch.countDown();
                    }
                }
            });
        }
        // 拒绝接受新的请求，执行完队列中的请求
        executor.shutdown();
        try {
            // 等待令牌数的完成
            downLatch.await();
        } catch (InterruptedException e) {

        }
        // 全部执行完毕，执行下一个子任务
        System.out.println("todo other --------");
    }

    @Test
    public void methodThreadPoolExecutor() {
        // 设置的令牌数
        int size = 100;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque());
        for (int i = 1; i <= size; i++) {
            // 控制并发数
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 具体业务
                    System.out.println("todo------" + finalI);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                    }
                }
            });
        }
        // 拒绝接受新的请求，执行完队列中的请求
        executor.shutdown();
        while (true) {
            try {
                if (executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.out.println("已完成！");
                    break;
                }
            } catch (InterruptedException e) {
            }
        }
        // 全部执行完毕，执行下一个子任务
        System.out.println("todo other--------");
    }

}
