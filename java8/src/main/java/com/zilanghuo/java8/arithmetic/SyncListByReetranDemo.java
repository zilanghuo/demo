package com.zilanghuo.java8.arithmetic;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author laiwufa
 * @date 2019/4/17 0017 上午 9:36
 */
public class SyncListByReetranDemo {

    public Vector<String> list = new Vector();

    public final static int maxSize = 10;

    ReentrantLock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public static void main(String[] args) {
        SyncListDemo listDemo = new SyncListDemo();
        // 两个生产者线程
        ExecutorService produceExecutor = Executors.newFixedThreadPool(2);
        ExecutorService consumerExecutor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            produceExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        listDemo.put(finalI + "");
                    } catch (Exception e) {

                    }
                    System.out.println("生产剩余个数：" + listDemo.getCount());
                }
            });
        }

        for (int i = 0; i < 100; i++) {
            consumerExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        listDemo.get();
                    } catch (Exception e) {
                    }
                    System.out.println("消费剩余个数：" + listDemo.getCount());
                }
            });
        }
        System.out.println("总个数：" + listDemo.getCount());
    }


    public String get() throws Exception {
        lock.lock();
        while (list.size() == 0) {
            System.out.println("缺货中，无法获取");
            consumer.wait();
        }
        Thread.sleep(10);
        String remove = list.remove(0);
        System.out.println("出货中：" + remove);
        this.notifyAll();
        lock.unlock();
        return remove;
    }

    public synchronized void put(String str) throws Exception {
        lock.lock();
        while (list.size() >= maxSize) {
            System.out.println("进货量过大，稍等");
            producer.wait();
        }
        Thread.sleep(10);
        list.add(str);
        System.out.println("进货中：" + str);
        this.notifyAll();
        lock.unlock();
    }

    public Integer getCount() {
        return list.size();
    }
}
