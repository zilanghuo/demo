package com.zilanghuo.java8.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadNameTest {

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getThreadGroup());


                }
            });

        }

    }

}
