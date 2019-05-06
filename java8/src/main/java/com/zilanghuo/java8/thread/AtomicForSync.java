package com.zilanghuo.java8.thread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Acer
 */
public class AtomicForSync {


    private static AtomicLong count = new AtomicLong(0);

    private static final int NUMBER = 10000;

    public static void main(String[] args) {
        Thread subtractThread = new VolatileNotAtomic.SubtractThread();
        subtractThread.start();
        count.getAndIncrement();
        count.getAndIncrement();
        for (int i = 0; i < NUMBER; i++) {
            count.incrementAndGet();
        }
        while (subtractThread.isAlive()) {
        }
        System.out.println("count 最后的值：" + count);
    }

    public static class SubtractThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < NUMBER; i++) {
                count.decrementAndGet();
            }
        }
    }

}
