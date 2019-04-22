package com.zilanghuo.java8.thread.memorynotvisible;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 下午 2:53
 */
public class Cancellation {

    public static void main(String[] args) throws Exception {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        Thread.sleep(3000);
        generator.cancel();
    }
}

class PrimeGenerator implements Runnable {
    private boolean cancelled;

    @Override
    public void run() {
        while (!cancelled) {
            System.out.println("running........");
        }
    }

    public void cancel() {
        this.cancelled = true;
        System.out.println("cancelled.......");
    }

}