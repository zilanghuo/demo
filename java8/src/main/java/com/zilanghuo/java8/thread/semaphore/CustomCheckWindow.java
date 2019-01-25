package com.zilanghuo.java8.thread.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 海关通道3个窗口，当某一个通道出现空闲，那么就安排排队人员进行服务
 */
public class CustomCheckWindow {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }
}

class SecurityCheckThread extends Thread {
    private int seq;
    private Semaphore semaphore;

    public SecurityCheckThread(int seq, Semaphore semaphore) {
        this.seq = seq;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println("no." + seq + "乘客，正在查验中");
            if (seq % 2 == 0) {
                Thread.sleep(1000);
                System.out.println("no." + seq + "身份可疑，不能登机！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println("No." + seq + "已完成服务。");
        }
    }
}