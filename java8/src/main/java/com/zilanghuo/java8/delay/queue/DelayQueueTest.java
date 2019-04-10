package com.zilanghuo.java8.delay.queue;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * @author laiwufa
 * @date 2019/4/10 0010 上午 10:08
 */
public class DelayQueueTest {


    public static void main(String[] args) throws Exception {
        DelayQueue<DelayQueueElement> delayQueue = new DelayQueue();
        produce(delayQueue);
        consumer(delayQueue);
        Thread.sleep(20 * 1000);

    }


    private static void consumer(DelayQueue<DelayQueueElement> delayQueue) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始消费数据：");
                while (true) {
                    try {
                        DelayQueueElement take = delayQueue.take();
                        System.out.println("当前时间：" + DateUtil.formatTime(new Date()) + ",创建对象：" + take.toString());
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }

    private static void produce(DelayQueue<DelayQueueElement> delayQueue) throws Exception {
        final int[] i = {1};
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始生产数据：");
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                    DelayQueueElement element = new DelayQueueElement(2000, "hello" + i[0]);
                    delayQueue.offer(element);
                    System.out.println("当前时间：" + DateUtil.formatTime(new Date()) + ",创建对象：" + element.getMsg() + ",延时：" + element.getDelay());
                    i[0]++;
                }
            }
        }).start();

    }

}
