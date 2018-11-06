package com.zilanghuo.java8.thread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author laiwufa
 * @date 2018/11/6
 * use:回环栅栏，等四个线程都执行完毕，才会执行下一步
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws Exception {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Write(cyclicBarrier).start();
        }
        cyclicBarrier.await();
        System.out.println("所有线程执行完毕！");
    }
}

class Write extends Thread {
    private CyclicBarrier cyclicBarrier;
    public Write(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {
        System.out.println("thread no." + Thread.currentThread().getName() + " processing!");
        try {
            Thread.sleep(3000);
            System.out.println("thread no." + Thread.currentThread().getName() + "is processed success!");
            cyclicBarrier.await();
        } catch (Exception e) {
        }
    }
}