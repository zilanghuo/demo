package com.zilanghuo.java8.thread;

/**
 * @author laiwufa
 * @date 2019/5/16 22:36
 * 通过运行jconsole.jar 可以检测到死锁的存在
 */
public class DeadThreadDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A.class) {

                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A.class) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B.class) {

                    }
                }
            }
        }).start();


    }

}

class A {

}

class B {

}