package com.zilanghuo.java8.thread.readWriteLock;

import java.util.Random;

/**
 * @author laiwufa
 * @date 2019/4/22 0022 上午 11:51
 * 读写锁
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        final QueueData queueData = new QueueData();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        queueData.get();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        queueData.put(new Random().nextInt(10000));
                    }
                }
            }).start();
        }


    }

}
